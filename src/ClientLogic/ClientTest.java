package ClientLogic;/*
 *Created by: Tom Ganemo
 *Date: YYYY-MM-DD
 *Project name
 *Comment about this project
 */

import DispatchClasses.*;
import GUI.*;
import GUI.QuestionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest extends JFrame implements ActionListener {

    PrintWriter out;
    ObjectInputStream in;
    String headLine;
    String alt1a;
    String alt2a;
    String alt3a;
    String alt4a;

    String toSendBackToServer = "This feedback";

    public ClientTest() {
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

            getContentPane().removeAll();
            WaitingPanel startingGame = new WaitingPanel();
            this.add(startingGame);
            this.revalidate();
            this.repaint();
            this.pack();
            this.setVisible(true);

            while(true) {
                Object informationPackFromServer = in.readObject();

                if (informationPackFromServer instanceof QuestionClass) {
                    objectInformationToStrings(informationPackFromServer);
                    getContentPane().removeAll();
                    QuestionPanel qp = new QuestionPanel(headLine, alt1a, alt2a, alt3a, alt4a, out);
                    this.add(qp);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    this.setVisible(true);
                }
                if (informationPackFromServer instanceof CategoryClass) {
                    objectInformationToStrings(informationPackFromServer);
                    getContentPane().removeAll();
                    CategoryPanel cp = new CategoryPanel(headLine, alt1a, alt2a, alt3a, alt4a, out);
                    this.add(cp);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    this.setVisible(true);
                }
                if (informationPackFromServer instanceof ScoreClass) {
                    objectInformationToStrings(informationPackFromServer);
                    getContentPane().removeAll();
                    ScorePanel jp1 = new ScorePanel(headLine,alt1a, alt2a, alt3a, alt4a);
                    this.add(jp1);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    this.setVisible(true);
                }
                if (informationPackFromServer instanceof WaitingClass) {
                    getContentPane().removeAll();
                    WaitingPanel jp1 = new WaitingPanel();
                    this.add(jp1);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    this.setVisible(true);
                }
                if (informationPackFromServer instanceof WinClass) {
                    getContentPane().removeAll();
                    WinPanel jp1 = new WinPanel();
                    this.add(jp1);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    this.setVisible(true);
                }
                if (informationPackFromServer instanceof LoseClass) {
                    getContentPane().removeAll();
                    LosePanel jp1 = new LosePanel();
                    this.add(jp1);
                    this.revalidate();
                    this.repaint();
                    this.pack();
                    this.setVisible(true);
                }
                if (informationPackFromServer instanceof TieClass) {
                    getContentPane().removeAll();
                    TiePanel jp1 = new TiePanel();
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
        String[] temp = fullString.split("-");
        headLine = temp[0];
        alt1a = temp[1];
        alt2a = temp[2];
        alt3a = temp[3];
        alt4a = temp[4];
    }

    public static void main(String[] args) {
        ClientTest ct = new ClientTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
