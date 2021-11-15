import java.util.ArrayList;
import java.util.List;

public class GameServer implements Runnable{

    int amountOfQuestion = 3; // change later !!!!!!!!!!!!!!!!!!!!!!!!
    int amountOfRounds = 3; // change later !!!!!!!!!!!!!!!!!!!!!!!!

    PlayerServer player1;
    PlayerServer player2;
    Thread activity = new Thread(this);
    QuestionGenerator qGenerator;
    String category;

    public GameServer(PlayerServer player1, PlayerServer player2) {
        activity.start();
        this.player1 = player1;
        this.player2 = player2;
        qGenerator = new QuestionGenerator();
    }

    public void run(){


        for (int j = 0; j < amountOfRounds; j++) {
            //TODO skicka till client, välj kategori
            if(j%2==0){
                category = player1.chooseCategory();
            }else{
                category = player2.chooseCategory();
            }
            List<QuestionClass> question = new ArrayList<>();
            for (int i = 0; i < amountOfQuestion; i++) {
                question.add(qGenerator.generateQuestion(category));
            }
            for (int i = 0; i < amountOfQuestion; i++) {
                player1.sendQuestion(question.get(i));
                player1.receiveAnswer();
            }
            for (int i = 0; i < amountOfQuestion; i++) {
                player2.sendQuestion(question.get(i));
                player2.receiveAnswer();
            }
        }
    }
}
