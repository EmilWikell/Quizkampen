import ClientLogic.QuestionClass;

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
            String temp = "Vad är störst?,1, 2, 3, 4";
            toClient.writeObject(question);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveAnswer(){
        try {
            String s = fromClient.readLine().trim();
            System.out.println(s);
            if(s.equals("CORRECT")){
                System.out.println("We received correct answer!");
                points += 5;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String chooseCategory() {
        //TODO send alternatives to user(four category's), wait for database.
        try{
            return fromClient.readLine();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(452);
        }
        System.exit(452);
        return "";
    }

}
