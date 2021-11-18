package ClientLogic;/*
 *Created by: Tom Ganemo
 *Date: YYYY-MM-DD
 *Project name
 *Comment about this project
 */

import Database.QuestionClass;
import GUI.*;
import DispatchClasses.QuestionClass;
import GUI.QuestionPanel;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest extends JFrame implements ActionListener {

    PrintWriter out;
    ObjectInputStream in;
    String theQuestion;
    String alt1a;
    String alt2a;
    String alt3a;
    String alt4a;

    String toSendBackToServer = "This feedback";
    String correctAnswere;
    Object lastObject;

    public ClientTest() throws ClassNotFoundException {
        setLayout(new GridLayout(1,1));
        setTitle(JOptionPane.showInputDialog(null, "Enter your name: "));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String hostName = "127.0.0.1";  //localhost
        int portNumber = 44444;

        try {
            Socket socket = new Socket(hostName, portNumber);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new ObjectInputStream(socket.getInputStream());

            //TODO Bryt ut informationen från objectet till strängar för att populera spelplan

                while(true) {
                    Object informationPackFromServer = in.readObject();
                    objectInformationToStrings(informationPackFromServer);


                    if (informationPackFromServer instanceof QuestionClass) {
                        objectInformationToStrings(informationPackFromServer);
                        getContentPane().removeAll();
                        QuestionPanel qp = new QuestionPanel(theQuestion, alt1a, alt2a, alt3a, alt4a, out);
                        this.add(qp);
                        this.revalidate();
                        this.repaint();
                        this.pack();
                        this.setVisible(true);
                    }

                    if (informationPackFromServer instanceof String) {  // OBS! det ska vara CategoryClass!!
                        objectInformationToStrings(informationPackFromServer);
                        getContentPane().removeAll();
                        CategoryPanel cp = new CategoryPanel(theQuestion, alt1a, alt2a, alt3a, alt4a, out);
                        this.add(cp);
                        this.revalidate();
                        this.repaint();
                        this.pack();
                        this.setVisible(true);
                    }

                    if (informationPackFromServer instanceof String) {  // OBS! det ska vara ScoreScreen!!
                        // Parametrar 1, dinaPointsDennaRuna 2, DinaPoängTotalt,
                        // 3, MotståndarePointsDennaRunda 4, motStåndareTotalt
                        // 5, Rubrik ScoreScreen
                        objectInformationToStrings(informationPackFromServer);
                        getContentPane().removeAll();
                        JPanel jp1 = new JPanel();
                        this.add(jp1);
                        this.revalidate();
                        this.repaint();
                        this.pack();
                        this.setVisible(true);
                    }
                    if (informationPackFromServer instanceof String) {  // OBS! det ska vara WaitingScreen!!
                        getContentPane().removeAll();
                        JPanel jp1 = new JPanel();
                        this.add(jp1);
                        this.revalidate();
                        this.repaint();
                        this.pack();
                        this.setVisible(true);
                    }


                }

        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
