public class GameServer implements Runnable{

    PlayerServer player1;
    PlayerServer player2;
    Thread activity = new Thread(this);

    public GameServer(PlayerServer player1, PlayerServer player2) {
        activity.start();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void run(){

    }
}
