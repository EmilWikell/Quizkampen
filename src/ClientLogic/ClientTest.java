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
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTest extends JFrame{

    PrintWriter out;
    ObjectInputStream in;
    String headLine;
    String alt1a;
    String alt2a;
    String alt3a;
    String alt4a;
    String myName;
    boolean gameRunning;

    public ClientTest() {
        setLayout(new GridLayout(1, 1));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        String hostName = "127.0.0.1";  //localhost
        int portNumber = 44444;

        while (true) {
            getContentPane().removeAll();
            setTitle(JOptionPane.showInputDialog(null, "Enter your name: "));
            this.myName = getTitle();

            try {
                Socket socket = new Socket(hostName, portNumber);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new ObjectInputStream(socket.getInputStream());
                out.println(getTitle());

                getContentPane().removeAll();
                WaitingPanel startingGame = new WaitingPanel();
                paintDisplay(startingGame);
                gameRunning = true;
                while (gameRunning) {
                    Object informationPackFromServer = in.readObject();

                    if (informationPackFromServer instanceof QuestionClass) {
                        objectInformationToStrings(informationPackFromServer);
                        getContentPane().removeAll();
                        QuestionPanel panel = new QuestionPanel(headLine, alt1a, alt2a, alt3a, alt4a, out);
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof CategoryClass) {
                        objectInformationToStrings(informationPackFromServer);
                        getContentPane().removeAll();
                        CategoryPanel panel = new CategoryPanel(headLine, alt1a, alt2a, alt3a, alt4a, out);
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof ScoreClass) {
                        objectInformationToStrings(informationPackFromServer);
                        getContentPane().removeAll();
                        ScorePanel panel = new ScorePanel(myName, headLine, alt1a, alt2a, alt3a, alt4a);
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof WaitingClass) {
                        getContentPane().removeAll();
                        WaitingPanel panel = new WaitingPanel();
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof WinClass) {
                        getContentPane().removeAll();
                        WinPanel panel = new WinPanel();
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof LoseClass) {
                        getContentPane().removeAll();
                        LosePanel panel = new LosePanel();
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof TieClass) {
                        getContentPane().removeAll();
                        TiePanel panel = new TiePanel();
                        paintDisplay(panel);
                    }
                    if (informationPackFromServer instanceof SurrenderClass) {
                        getContentPane().removeAll();
                        SurrenderPanel panel = new SurrenderPanel();
                        paintDisplay(panel);
                        Thread.sleep(3000);
                        gameRunning = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }

    public void objectInformationToStrings(Object object){
        System.out.println(object.toString());
        String fullString = object.toString();
        String[] temp = fullString.split("-");
        headLine = temp[0];
        alt1a = temp[1];
        alt2a = temp[2];
        alt3a = temp[3];
        alt4a = temp[4];
    }
    private void paintDisplay(JPanel panel){
        this.add(panel);
        this.revalidate();
        this.repaint();
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ClientTest ct = new ClientTest();
    }
}
