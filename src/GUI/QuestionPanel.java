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
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import javax.swing.*;
import ClientLogic.ClientTest;


public class QuestionPanel extends JPanel implements ActionListener {

    ClientTest ct;
    JLabel questionLabel = new JLabel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();

    private String question;
    private String alt1;
    private String alt2;
    private String alt3;
    private String alt4;
    private String correctAnswer;
    private List<String> options = new ArrayList<>();
    private PrintWriter out;

    public QuestionPanel(String question, String alt1, String alt2, String alt3, String alt4, PrintWriter out) throws IOException, ClassNotFoundException {
        this.question = question;
        this.alt1 = alt1;
        this.alt2 = alt2;
        this.alt3 = alt3;
        this.alt4 = alt4;
        this.correctAnswer = alt4;
        this.out = out;
        this.options = generateListAndshuffle(alt1, alt2, alt3, alt4);

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(5, 1));


        questionLabel.setText("<html>"+ this.question +"</html>");
        this.add(questionLabel);

        button1.setText(options.get(0));
        button1.addActionListener(this);
        this.add(button1);

        button2.setText(options.get(1));
        button2.addActionListener(this);
        this.add(button2);

        button3.setText(options.get(2));
        button3.addActionListener(this);
        this.add(button3);

        button4.setText(options.get(3));
        button4.addActionListener(this);
        this.add(button4);
        this.setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        //removeGUI();  ??? Hur löser vi att det ska försvinna och repaintas osv...

        if(e.getSource()== button1){
            if(Objects.equals(button1.getText(), correctAnswer)){
                System.out.println("Correct answer!");
                button1.setBackground(Color.GREEN);
                returnToServer("CORRECT");
            }
            else{
                System.out.println("Wrong answer!");
            }
        }

        if(e.getSource()== button2){
            if(Objects.equals(button2.getText(), correctAnswer)){
                System.out.println("Correct answer!");
                button2.setBackground(Color.GREEN);
                returnToServer("CORRECT");
                //removeGUI();  ???
            }
            else{
                System.out.println("Wrong answer!");
            }
        }

        if(e.getSource()== button3){
            if(Objects.equals(button3.getText(), correctAnswer)){
                System.out.println("Correct answer!");
                button3.setBackground(Color.GREEN);
                returnToServer("CORRECT");
            }
            else{
                System.out.println("Wrong answer!");
            }
        }
        if(e.getSource()== button4){
            if(Objects.equals(button4.getText(), correctAnswer)){
                System.out.println("Correct answer!");
                button4.setBackground(Color.GREEN);
                returnToServer("CORRECT");
                //event();
            }
            else{
                System.out.println("Wrong answer!");
            }
        }

    }


    public List generateListAndshuffle(String item1,String item2, String item3, String item4){
        this.options.add(item1);
        this.options.add(item2);
        this.options.add(item3);
        this.options.add(item4);
        Collections.shuffle(options);
        return this.options;
    }

    private void returnToServer(String string) {
        System.out.println("we sent back info to server");
        out.println(string);
    }


    }




