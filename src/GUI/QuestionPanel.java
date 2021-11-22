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
import javax.swing.Timer;

import ClientLogic.ClientTest;


public class QuestionPanel extends JPanel implements ActionListener {

    JLabel questionLabel = new JLabel();
    private String question;
    private String alt1;
    private String alt2;
    private String alt3;
    private String alt4;
    private String correctAnswer;
    private List<String> options = new ArrayList<>();
    private PrintWriter out;
    private List<Button> buttonList = new ArrayList<>();
    private JProgressBar bar = new JProgressBar(0, 20);
    private Timer timer ;

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
        this.setLayout(new GridLayout(6, 1));

        questionLabel.setText("<html>"+ this.question +"</html>");
        this.add(questionLabel);

        for (int i = 0; i < options.size(); i++) {
            Button button = new Button();
            button.setText(options.get(i));
            button.addActionListener(this);
            add(button);
            buttonList.add(button);
        }
        bar.setValue(20);
        bar.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,12)) ;
        bar.setForeground(Color.red);
        bar.setOpaque(true);
        add(bar) ;
        fill();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //removeGUI();  ??? Hur löser vi att det ska försvinna och repaintas osv...
        Button jb;
        jb = (Button) e.getSource();

        if(jb.getText().equals(correctAnswer)){
            for (JButton b: buttonList) {
                b.setEnabled(false);
            }
            jb.setBackground(Color.GREEN);
            timer.stop();
            jb.setOpaque(true);
            jb.repaint();
            jb.revalidate();
            returnToServer("CORRECT");
        }
        else{
            jb.setBackground(Color.RED);
            jb.setOpaque(true);
            timer.stop();
            jb.repaint();
            jb.revalidate();
            for (JButton b: buttonList) {
                b.setEnabled(false);
                if (b.getText().equals(correctAnswer)){
                    b.setBackground(new Color(176, 252, 153));
                    jb.setOpaque(true);
                    jb.repaint();
                    jb.revalidate();
                }
                else{
                    b.setBackground(new Color(252, 139, 154));
                    jb.setOpaque(true);
                    jb.repaint();
                    jb.revalidate();
                }
            }
            returnToServer("FALSE");
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

    public void fill() {
        ActionListener listener = new ActionListener() {
            int counter = 20;
            @Override
            public void actionPerformed(ActionEvent ae) {
                counter--;
                bar.setValue(counter);
                if (counter == 0) {
                    for (Button b: buttonList) {
                        b.setEnabled(false);
                    }
                    returnToServer("False");
                    timer.stop();
                }
            }
        };
        timer = new Timer(1000, listener);
        timer.start();
    }

    private void returnToServer(String string) {
        System.out.println("we sent back info to server");
        out.println(string);
    }


}




