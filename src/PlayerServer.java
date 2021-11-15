import java.io.*;
import java.net.Socket;

public class PlayerServer {
    private Socket socket;
    private ObjectOutput toClient;
    private BufferedReader fromClient;

    public PlayerServer(Socket socket) {
        this.socket = socket;
        try {
            toClient = new ObjectOutputStream(socket.getOutputStream());
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sendQuestion(QuestionClass question){
        try {
            toClient.writeObject(question);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
