package Server;

import java.util.List;

import DAO.DAO;
import DipatchHandlers.CategoryHandler;
import DispatchClasses.QuestionClass;
import DispatchClasses.ScoreClass;

public class GameServer implements Runnable {

    int amountOfQuestion = 3; // change later !!!!!!!!!!!!!!!!!!!!!!!!
    int amountOfRounds = 3; // change later !!!!!!!!!!!!!!!!!!!!!!!!

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
        activity.start();
    }

    public void run() {

        for (int j = 0; j < amountOfRounds; j++) {

            String chosenCategory;
            if (j % 2 == 0) {
                chosenCategory = player1.chooseCategory(categoryHandler);
                //TODO player 2 waiting screen
            } else {
                chosenCategory = player2.chooseCategory(categoryHandler);
                //TODO player 1 waiting screen
            }

            List<QuestionClass> chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);

            //TODO player 2 waiting screen
            for (int i = 0; i < amountOfQuestion; i++) {
                player1.sendQuestion(chosenQuestions.get(i));
                player1.receiveAnswer();
            }

            //TODO player 1 waiting screen
            for (int i = 0; i < amountOfQuestion; i++) {
                player2.sendQuestion(chosenQuestions.get(i));
                player2.receiveAnswer();
            }

            player1.sendScore();
            player2.sendScore();
            //TODO score, wait for ok
        }
        //TODO display winner
    }
}
