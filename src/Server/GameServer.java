package Server;

import DAO.DAO;
import DipatchHandlers.CategoryHandler;
import DispatchClasses.QuestionClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class GameServer implements Runnable {

    Boolean stillRunning = true;
    int amountOfQuestion;
    int amountOfRounds;
    PlayerServer player1;
    PlayerServer player2;
    DAO dao;
    CategoryHandler categoryHandler;
    Thread activity = new Thread(this);
    boolean gameRunning = true;

    public GameServer(DAO dao, PlayerServer player1, PlayerServer player2) {
        this.dao = dao;
        categoryHandler = new CategoryHandler(this.dao);
        this.player1 = player1;
        this.player2 = player2;
        this.player1.setOpp(player2);
        this.player2.setOpp(player1);

        Properties p = new Properties();
        try {
            p.load(new FileInputStream("src/DAO/QuestAndRounds.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        amountOfQuestion = Integer.parseInt(p.getProperty("Questions"));
        amountOfRounds = Integer.parseInt(p.getProperty("Rounds"));

        activity.start();
    }

    public void run() {
        player1.setName();
        player2.setName();

        while (gameRunning) {
            for (int j = 0; j < amountOfRounds && stillRunning; j++) {
                String chosenCategory;
                try {
                    if (j % 2 == 0) {
                        chosenCategory = getCategoryFromClient(player1, player2);
                    } else {
                        chosenCategory = getCategoryFromClient(player2, player1);
                    }
                } catch (StopGameException e) {
                    break;
                }
                List<QuestionClass> chosenQuestions;
                if (j % 2 == 0) {
                    try {
                        chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);
                    } catch (IOException | NullPointerException e) {
                        stopGame(player2);
                        break;
                    }
                } else {
                    try {
                        chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);
                    } catch (IOException | NullPointerException e) {
                        stopGame(player1);
                        break;
                    }
                }
                try {
                    if (j % 2 == 0) {
                        sendQuestionAndWaitScreen(player1, player2, chosenQuestions);
                        sendQuestionAndWaitScreen(player2, player1, chosenQuestions);
                    } else {
                        sendQuestionAndWaitScreen(player2, player1, chosenQuestions);
                        sendQuestionAndWaitScreen(player1, player2, chosenQuestions);
                    }
                } catch (StopGameException e) {
                    break;
                }
                player1.sendScore();
                player2.sendScore();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                player1.resetMyPointsRound();
                player2.resetMyPointsRound();
            }
            if (stillRunning) {
                player1.sendWinningScreen();
                player2.sendWinningScreen();
                player1.resetMyPointsTotal();
                player2.resetMyPointsTotal();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getCategoryFromClient(PlayerServer choosing, PlayerServer waiting) throws StopGameException {
        try {
            waiting.sendWaitScreen();
        } catch (IOException  | NullPointerException e) {
            stopGame(choosing);
            throw new StopGameException();
        }
        try {
            return  choosing.chooseCategory(categoryHandler);
        } catch (IOException | NullPointerException e) {
            stopGame(waiting);
            throw new StopGameException();
        }
    }

    private void sendQuestionAndWaitScreen(PlayerServer playing, PlayerServer waiting,
                                           List<QuestionClass> chosenQuestions) throws StopGameException {
        try {
            waiting.sendWaitScreen();
        }catch (IOException  | NullPointerException e){
            stopGame(playing);
            throw new StopGameException();
        }
        try {
            for (int i = 0; i < amountOfQuestion; i++) {
                playing.sendQuestion(chosenQuestions.get(i));
                try {
                    playing.receiveAnswer();
                } catch (IOException  | NullPointerException e) {
                    stopGame(waiting);
                    throw new StopGameException();
                }
            }
        }catch (IOException  | NullPointerException e){
            stopGame(waiting);
            throw new StopGameException();
        }
    }

    private void stopGame(PlayerServer remainingPlayer) {
        remainingPlayer.sendSurrender();
        stillRunning = false;
    }
}
