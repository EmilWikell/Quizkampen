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

        for (int j = 0; j < amountOfRounds; j++) {

            String chosenCategory;
            if (j % 2 == 0) {
                player2.sendWaitScreen();
                chosenCategory = player1.chooseCategory(categoryHandler);
            } else {
                player1.sendWaitScreen();
                chosenCategory = player2.chooseCategory(categoryHandler);
            }

            List<QuestionClass> chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);

            if (j % 2 == 0) {
               whosTurn(player1, player2, chosenQuestions);
            } else {
                whosTurn(player2, player1, chosenQuestions);
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
        player1.sendWinningScreen();
        player2.sendWinningScreen();
        //TODO display winner
    }

    private void whosTurn(PlayerServer playingFirst, PlayerServer waitingFirst, List<QuestionClass> chosenQuestions ) {
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
