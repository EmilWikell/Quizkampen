package GUI;/*
 *Created by: Tom Ganemo
 *Date: YYYY-MM-DD
 *Project name
 *Comment about this project
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import ClientLogic.ClientTest;


public class QuestionPanel extends JPanel implements ActionListener {

    ClientTest ct;
    JLabel questionLabel = new JLabel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();

    public QuestionPanel(String question, String alt1, String alt2, String alt3, String alt4) throws IOException, ClassNotFoundException {

        setPreferredSize(new Dimension(400, 900));

        this.setPreferredSize(new Dimension(600, 1000));
        this.setLayout(new GridLayout(5, 1));


        questionLabel.setText(question);
        this.add(questionLabel);

        button1.setText(alt1);
        button1.addActionListener(this);
        this.add(button1);

        button2.setText(alt2);
        button2.addActionListener(this);
        this.add(button2);

        button3.setText(alt3);
        button3.addActionListener(this);
        this.add(button3);

        button4.setText(alt4);
        button4.addActionListener(this);
        this.add(button4);

        this.setVisible(true);
}


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== button1){
            System.out.println("Wrong answer!");
        }
        if(e.getSource()== button2){
            System.out.println("Wrong answer!");
        }
        if(e.getSource()== button3){
            System.out.println("Wrong answer!");
        }
        if(e.getSource()== button4){
            System.out.println("Correct answer!");
            button4.setBackground(Color.GREEN);
            ct.returnToServer("CORRECT");

            // TODO kanske bara returna en Integer 1 eller 0 beroende på poäng.
        }

    }


    }




