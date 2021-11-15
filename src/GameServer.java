public class GameServer implements Runnable{

    PlayerServer player1;
    PlayerServer player2;
    Thread activity = new Thread(this);
    Boolean gameRound = true;
    QuestionGenerator qGenerator;

    public GameServer(PlayerServer player1, PlayerServer player2) {
        activity.start();
        this.player1 = player1;
        this.player2 = player2;
        qGenerator = new QuestionGenerator();
    }

    public void run(){

        while (gameRound){
            QuestionClass question = qGenerator.generateQuestion();
            player1.sendQuestion(question);
            player2.sendQuestion(question);

            // tar emot rätt eller fel

            //gameround ++
        }
    }
}
