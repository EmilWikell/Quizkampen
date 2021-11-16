/*
 *Created by: Tom Ganemo
 *Date: YYYY-MM-DD
 *Project name
 *Comment about this project
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

public class ClientTest implements ActionListener {

    JFrame jf = new JFrame();
    JPanel questionPanel = new JPanel();
    JLabel question = new JLabel();
    JButton alt1 = new JButton();
    JButton alt2 = new JButton();
    JButton alt3 = new JButton();
    JButton alt4 = new JButton();
    PrintWriter out;
    ObjectInputStream in;

    String theQuestion;
    String alt1a;
    String alt2a;
    String alt3a;
    String alt4a;

    public String getToSendBackToServer() {
        return toSendBackToServer;
    }
    public void setToSendBackToServer(String toSendBackToServer) {
        this.toSendBackToServer = toSendBackToServer;
    }
    String toSendBackToServer = "This feedback";


    String correctAnswere;

    ClientTest() throws IOException, ClassNotFoundException {

        String hostName = "127.0.0.1";  //localhost
        int portNumber = 55551;

        try {
            Socket socket = new Socket(hostName, portNumber);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new ObjectInputStream(socket.getInputStream());

            Object questionPackFromServer = in.readObject();
            objectInformationToStrings(questionPackFromServer);
            //TODO Bryt ut informationen från objectet till strängar för att populera spelplan

            jf.add(questionPanel);
            jf.setVisible(true);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            questionPanel.setPreferredSize(new Dimension(400, 900));
            questionPanel.setLayout(new GridLayout(5, 1));
            questionPanel.setVisible(true);

            question.setText(theQuestion);
            questionPanel.add(question);

            alt1.setText(alt1a);
            alt1.addActionListener(this);
            questionPanel.add(alt1);


            alt2.setText(alt2a);
            alt2.addActionListener(this);
            questionPanel.add(alt2);

            alt3.setText(alt3a);
            alt3.addActionListener(this);
            questionPanel.add(alt3);

            alt4.setText(alt4a);
            alt4.addActionListener(this);
            questionPanel.add(alt4);

            jf.setLocationRelativeTo(null);
            jf.pack();
            jf.setVisible(true);

        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }


    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== alt1){
            System.out.println("Wrong answere!");

        }

        if(e.getSource()== alt2){
            System.out.println("Wrong answere!");

        }
        if(e.getSource()== alt3){
            System.out.println("Wrong answere!");

        }
        if(e.getSource()== alt4){
            System.out.println("Correct Answere");
            alt4.setBackground(Color.GREEN);
            returnToServer();
            // TODO kanske bara returna en Integer 1 eller 0 beroende på poäng.
        }

    }

    public void returnToServer(){
        setToSendBackToServer("CORRECT");
        System.out.println("we sent back info to server");
        out.println(getToSendBackToServer());
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

        //fullString.split(",");
//
//        String theQuestion;
//        String alt1a;
//        String alt2a;
//        String alt3a;
//        String alt4a;

    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientTest ct = new ClientTest();
    }


}
