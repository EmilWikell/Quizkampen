package Server;

import DAO.DAO;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {
    public static void main(String[] args) {new ServerListener();}

    public ServerListener(){
        DAO dao = new DAO();
        try {
            ServerSocket listener = new ServerSocket(44444);
            while (true){
                PlayerServer player1 = new PlayerServer(listener.accept());
                PlayerServer player2 = new PlayerServer(listener.accept());
                GameServer game = new GameServer(dao,player1, player2);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
