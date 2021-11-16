
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
        QuestionClass question = new QuestionClass("Vad är störst?","1", "2", "3","4");
            for (int i = 0; i < amountOfQuestion; i++) {
                player1.sendQuestion(question);
                player1.receiveAnswer();
                //TODO player 2 waiting screen
            }
            for (int i = 0; i < amountOfQuestion; i++) {
                player2.sendQuestion(question);
                player2.receiveAnswer();
                //TODO player 1 waiting screen
            }
            //TODO score, wait for ok
        }
        //TODO display winner
    }
