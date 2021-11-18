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

            player2.sendWaitScreen();
            for (int i = 0; i < amountOfQuestion; i++) {
                player1.sendQuestion(chosenQuestions.get(i));
                player1.receiveAnswer();
            }

            player1.sendWaitScreen();
            for (int i = 0; i < amountOfQuestion; i++) {
                player2.sendQuestion(chosenQuestions.get(i));
                player2.receiveAnswer();
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
}
