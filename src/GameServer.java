import java.util.List;

import DAO.DAO;
import Database.CategoryClass;
import Database.QuestionClass;

public class GameServer implements Runnable{

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

    public void run(){


        /*for (int j = 0; j < amountOfRounds; j++) {
            if(j%2==0){
                category = player1.chooseCategory();
                //TODO player 2 waiting screen
            }else{
                category = player2.chooseCategory();
                //TODO player 1 waiting screen
            }
            List<QuestionClass> question = new ArrayList<>();
            for (int i = 0; i < amountOfQuestion; i++) {
                question.add(qGenerator.generateQuestion(category));
            }
         */
        String chosenCategory = "SPORTS";

        List<QuestionClass> chosenQuestions = dao.getQuestions(amountOfQuestion, chosenCategory);
            for (int i = 0; i < amountOfQuestion; i++) {
                player1.sendQuestion(chosenQuestions.get(i));
                player1.receiveAnswer();
                //TODO player 2 waiting screen
            }
            for (int i = 0; i < amountOfQuestion; i++) {
                player2.sendQuestion(chosenQuestions.get(i));
                player2.receiveAnswer();
                //TODO player 1 waiting screen
            }
            //TODO score, wait for ok
        }
        //TODO display winner
    }
