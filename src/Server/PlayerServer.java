package Server;

import DipatchHandlers.CategoryHandler;
import DipatchHandlers.ScoreHandler;
import DispatchClasses.QuestionClass;
import DispatchClasses.ScoreClass;

import java.io.*;
import java.net.Socket;

public class PlayerServer {
    private Socket socket;
    private ObjectOutput toClient;
    private BufferedReader fromClient;
    int points;
    ScoreHandler scoreHandler;
    private PlayerServer opp;

    public PlayerServer(Socket socket) {
        scoreHandler = new ScoreHandler();
        points = 0;
        this.socket = socket;
        try {
            toClient = new ObjectOutputStream(socket.getOutputStream());
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setOpp(PlayerServer opp){
        this.opp = opp;
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
    public String chooseCategory(CategoryHandler categoryHandler) {
        try{
            toClient.writeObject(categoryHandler.getSmallListOfCategories());
            String chosenCategory = fromClient.readLine();
            categoryHandler.removeChosenCategory(chosenCategory);
            return chosenCategory;
        }catch (Exception e){
            e.printStackTrace();
            System.exit(452);
            return "";
            //TODO ??
        }
    }

    public void sendScore() {
        try {
            toClient.writeObject(new ScoreClass(scoreHandler.getScoreTotal(),
                    scoreHandler.getScoreThisRound(),
                    opp.scoreHandler.getScoreTotal(),
                    opp.scoreHandler.getScoreThisRound()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
