package Server;

import DAO.DAO;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {
    public static void main(String[] args) {new ServerListener();}

    public ServerListener(){
        DAO dao = new DAO();
        try {
            ServerSocket listener = new ServerSocket(55551);
            while (true){
                System.out.println("Server started");
                PlayerServer player1 = new PlayerServer(listener.accept());
                System.out.println("1st person connected");
                PlayerServer player2 = new PlayerServer(listener.accept());
                System.out.println("2nd person connected");
                GameServer game = new GameServer(dao,player1, player2);
                System.out.println("Game created");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
