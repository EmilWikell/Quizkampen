package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import DAO.DAO;
import DipatchHandlers.CategoryHandler;
import DispatchClasses.QuestionClass;

public class GameServer implements Runnable {

    Boolean stillRunning = true;
    int amountOfQuestion;
    int amountOfRounds;
    PlayerServer player1;
    PlayerServer player2;
    DAO dao;
    CategoryHandler categoryHandler;
    Thread activity = new Thread(this);

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
        amountOfQuestion = Integer.parseInt(p.getProperty("Questions", "3"));
        amountOfRounds = Integer.parseInt(p.getProperty("Rounds", "3"));

        activity.start();
    }

    public void run() {
        player1.setName();
        player2.setName();

        for (int j = 0; j < amountOfRounds && stillRunning; j++) {

            String chosenCategory;
            try {
                if (j % 2 == 0) {
                    chosenCategory = getCategoryFromClient(player1, player2);
                } else {
                    chosenCategory = getCategoryFromClient(player2, player1);
                }
            }catch (StopGameException e){
                break;
            }

            List<QuestionClass> chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);

            try {
                if (j % 2 == 0) {

                    sendQuestionAndWaitScreen(player1,player2,chosenQuestions);
                    sendQuestionAndWaitScreen(player2, player1, chosenQuestions);

                } else {
                    sendQuestionAndWaitScreen(player2, player1, chosenQuestions);
                    sendQuestionAndWaitScreen(player1,player2,chosenQuestions);
                }
            }catch (StopGameException e){
                break;
            }

            player1.sendScore();
            player2.sendScore();

            try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}

            player1.resetMyPointsRound();
            player2.resetMyPointsRound();
        }
        if (stillRunning){
            player1.sendWinningScreen();
            player2.sendWinningScreen();
        }
    }

    private String getCategoryFromClient(PlayerServer choosing, PlayerServer waiting) throws StopGameException {
        try {
            waiting.sendWaitScreen();
        } catch (IOException e) {
            stopGame(choosing);
            throw new StopGameException();
        }
        try {
            return  choosing.chooseCategory(categoryHandler);
        } catch (IOException e) {
            stopGame(waiting);
            throw new StopGameException();
        }
    }

    private void sendQuestionAndWaitScreen(PlayerServer playing, PlayerServer waiting,
                                           List<QuestionClass> chosenQuestions) throws StopGameException {
        try {
            waiting.sendWaitScreen();
        }catch (IOException e){
            stopGame(playing);
            throw new StopGameException();
        }
        try {
            for (int i = 0; i < amountOfQuestion; i++) {
                playing.sendQuestion(chosenQuestions.get(i));
                playing.receiveAnswer();
            }
        }catch (IOException e){
            stopGame(waiting);
            throw new StopGameException();
        }
    }

    private void stopGame(PlayerServer remainingPlayer) {
        remainingPlayer.sendSurrender();
        stillRunning = false;
    }
}
