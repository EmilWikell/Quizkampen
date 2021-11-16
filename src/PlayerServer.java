import java.io.*;
import java.net.Socket;

public class PlayerServer {
    Socket socket;
    PrintWriter toClient;
    BufferedReader fromClient;

    public PlayerServer(Socket socket) {
        this.socket = socket;
        try {
            toClient = new PrintWriter((socket.getOutputStream()),true);
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
