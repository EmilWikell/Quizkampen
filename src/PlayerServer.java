import java.io.*;
import java.net.Socket;

public class PlayerServer {
    private Socket socket;
    private ObjectOutput toClient;
    private BufferedReader fromClient;
    int points;

    public PlayerServer(Socket socket) {
        points = 0;
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
    public void receiveAnswer(){
        try {
            String s = fromClient.readLine();
            if(s.equals("CORRECT")){
                points += 5;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String chooseCategory(){
        String availableCategory = "djur,sport,historia,it";

        return "djur"; //hårdkodat ändra senare
    }

}
