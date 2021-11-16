/*
 *Created by: Tom Ganemo
 *Date: YYYY-MM-DD
 *Project name
 *Comment about this project
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ClientTest {

    JFrame jf = new JFrame();
    JPanel questionPanel = new JPanel();
    JLabel question = new JLabel();
    JButton alt1 = new JButton();
    JButton alt2 = new JButton();
    JButton alt3 = new JButton();
    JButton alt4 = new JButton();

    String correctAnswere;


    ClientTest() {

        jf.add(questionPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        questionPanel.setPreferredSize(new Dimension(400, 900));
        questionPanel.setLayout(new GridLayout(5, 1));
        questionPanel.setVisible(true);


        question.setText("STRING 1 från servern");
        questionPanel.add(question);

        alt1.setText("alternative 1");
        questionPanel.add(alt1);

        alt2.setText("alternative 2");
        questionPanel.add(alt2);

        alt3.setText("alternative 3");
        questionPanel.add(alt3);

        alt4.setText("alternative 4");
        questionPanel.add(alt4);

        jf.setLocationRelativeTo(null);
        jf.pack();
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        ClientTest ct = new ClientTest();
    }


}
