import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {
    public static void main(String[] args) {new ServerListener();}

    public ServerListener(){
    try {
        ServerSocket listener = new ServerSocket(55555);
        while (true){
            PlayerServer player1 = new PlayerServer(listener.accept());
            PlayerServer player2 = new PlayerServer(listener.accept());
            GameServer game = new GameServer(player1, player2);
        }
    }catch (IOException e){
        e.printStackTrace();
    }
    }
}
