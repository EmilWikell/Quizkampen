package Server;

import java.util.ArrayList;
import java.util.List;

import DAO.DAO;
import DispatchClasses.CategoryClass;
import DispatchClasses.QuestionClass;

public class GameServer implements Runnable {

    int amountOfQuestion = 3; // change later !!!!!!!!!!!!!!!!!!!!!!!!
    int amountOfRounds = 3; // change later !!!!!!!!!!!!!!!!!!!!!!!!

    PlayerServer player1;
    PlayerServer player2;
    DAO dao;
    CategoryClass categoryHandler;
    Thread activity = new Thread(this);

    public GameServer(DAO dao, PlayerServer player1, PlayerServer player2) {
        this.dao = dao;
        categoryHandler = new CategoryClass(this.dao);
        this.player1 = player1;
        this.player2 = player2;
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
            //TODO score, wait for ok
        }
        //TODO display winner
    }
}
