package ClientLogic;/*
 *Created by: Tom Ganemo
 *Date: YYYY-MM-DD
 *Project name
 *Comment about this project
 */

import Database.QuestionClass;
import GUI.CategoryPanel;
import GUI.QuestionPanel;

import java.io.*;
import java.net.Socket;
import javax.swing.*;

public class ClientTest {

    static PrintWriter out;
    ObjectInputStream in;
    String theQuestion;
    String alt1a;
    String alt2a;
    String alt3a;
    String alt4a;

    String toSendBackToServer = "This feedback";
    String correctAnswere;


    ClientTest() throws IOException, ClassNotFoundException {

        String hostName = "127.0.0.1";  //localhost
        int portNumber = 55551;

        try {
            Socket socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new ObjectInputStream(socket.getInputStream());

            Object informationPackFromServer = in.readObject();
            objectInformationToStrings(informationPackFromServer);
            //TODO Bryt ut informationen från objectet till strängar för att populera spelplan

             if(informationPackFromServer instanceof QuestionClass) {
                 JFrame jf = new JFrame();
                 jf.add(new QuestionPanel(theQuestion, alt1a, alt2a, alt3a, alt4a));
                 jf.pack();
                 jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                 jf.setLocationRelativeTo(null);
                 jf.setVisible(true);
             }

//            if(informationPackFromServer instanceof QuestionClass){  // OBS! det ska vara CategoryClass!!
//                JFrame jf = new JFrame();
//                jf.add(new CategoryPanel(theQuestion, alt1a, alt2a, alt3a, alt4a));
//                jf.pack();
//                jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                jf.setLocationRelativeTo(null);
//                jf.setVisible(true);
//           }

        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }

    }

    public static void returnToServer(String string){
        System.out.println("we sent back info to server");
        out.println(string);
    }

    public void objectInformationToStrings(Object object){
        System.out.println(object.toString());
        String fullString = object.toString();
        //fullString.split(",");
        String[] temp = fullString.split(",");
        theQuestion = temp[0];
        alt1a = temp[1];
        alt2a = temp[2];
        alt3a = temp[3];
        alt4a = temp[4];
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientTest ct = new ClientTest();
    }


}
