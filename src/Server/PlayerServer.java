package Server;

import DipatchHandlers.CategoryHandler;
import DipatchHandlers.ScoreHandler;
import DispatchClasses.QuestionClass;
import DispatchClasses.ScoreClass;
import DispatchClasses.SurrenderClass;
import DispatchClasses.WaitingClass;

import java.io.*;
import java.net.Socket;

public class PlayerServer {
    String name;
    private Socket socket;
    private ObjectOutput toClient;
    private BufferedReader fromClient;
    int points;
    ScoreHandler scoreHandler;
    WaitingClass wait;
    private PlayerServer opp;

    public PlayerServer(Socket socket) {
        scoreHandler = new ScoreHandler();
        wait = new WaitingClass();
        points = 0;
        this.socket = socket;
        try {
            toClient = new ObjectOutputStream(socket.getOutputStream());
            fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(Exception e){
            System.out.println("vi är på skivare");
            e.printStackTrace();
        }
    }

    public void setName() {
        try {
            this.name = fromClient.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setOpp(PlayerServer opp){
        this.opp = opp;
    }

    public void sendQuestion(QuestionClass question) throws IOException,NullPointerException {
        toClient.writeObject(question);
    }
    public void receiveAnswer() throws IOException,NullPointerException {
        String s;
        s = fromClient.readLine();
        System.out.println(s);
        if(s.equals("CORRECT")){
            scoreHandler.increaseScore();
        }
        try {
            Thread.sleep(2325);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
    public String chooseCategory(CategoryHandler categoryHandler) throws IOException, NullPointerException {

            toClient.writeObject(categoryHandler.getSmallListOfCategories());
            String chosenCategory = fromClient.readLine();
            categoryHandler.removeChosenCategory(chosenCategory);
            return chosenCategory;

    }

    public void sendScore() {
        try {
            toClient.writeObject(new ScoreClass(opp.name, scoreHandler.getScoreTotal(),
                    scoreHandler.getScoreThisRound(),
                    opp.scoreHandler.getScoreTotal(),
                    opp.scoreHandler.getScoreThisRound()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendWaitScreen() throws IOException,NullPointerException{
            toClient.writeObject(wait);
    }
    public void resetMyPointsRound(){
        scoreHandler.resetRound();
    }
    public void resetMyPointsTotal(){
        scoreHandler.resetTotal();
    }

    public void sendWinningScreen() {
        int oppScore = opp.scoreHandler.getScoreTotal();
        try {
            toClient.writeObject(scoreHandler.getWinner(oppScore));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendSurrender(){
        try {
            toClient.writeObject(new SurrenderClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
