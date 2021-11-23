package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import DAO.DAO;
import DipatchHandlers.CategoryHandler;
import DispatchClasses.QuestionClass;
import DispatchClasses.ScoreClass;

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

            String chosenCategory = null;
            if (j % 2 == 0) {
                player2.sendWaitScreen();
                try {
                    chosenCategory = player1.chooseCategory(categoryHandler);
                } catch (IOException e) {
                    player2.sendSurrender();
                    activity.interrupt();
                }
            } else {
                player1.sendWaitScreen();
                try {
                    chosenCategory = player2.chooseCategory(categoryHandler);
                } catch (IOException e) {
                    player1.sendSurrender();
                    activity.interrupt();
                }
            }

            List<QuestionClass> chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);

            if (j % 2 == 0) {
                try {
                    whosTurn(player1, player2, chosenQuestions);
                }catch (IOException e){
                    player2.sendSurrender();
                    activity.interrupt();
                    stillRunning = false;
                    break;
                }

            } else {
                try{
                    whosTurn(player2, player1, chosenQuestions);
                }catch (IOException e){
                player1.sendSurrender();
                activity.interrupt();
                stillRunning = false;
                break;
                }
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
        if (stillRunning){
            player1.sendWinningScreen();
            player2.sendWinningScreen();
        }
    }

    private void whosTurn(PlayerServer playingFirst, PlayerServer waitingFirst,
                          List<QuestionClass> chosenQuestions ) throws  IOException{
        waitingFirst.sendWaitScreen();
        for (int i = 0; i < amountOfQuestion; i++) {
                playingFirst.sendQuestion(chosenQuestions.get(i));
                playingFirst.receiveAnswer();

        }

        playingFirst.sendWaitScreen();
        for (int i = 0; i < amountOfQuestion; i++) {
            waitingFirst.sendQuestion(chosenQuestions.get(i));
            waitingFirst.receiveAnswer();
        }
    }
}
