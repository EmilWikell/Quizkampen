package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    public QuestionPanel(String question, String alt1, String alt2, String alt3, String alt4, PrintWriter out) {
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
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18)) ;
        questionLabel.setBackground(Color.lightGray.brighter());
        questionLabel.setForeground(new Color (103, 51, 150)) ;
        questionLabel.setOpaque(true);
        this.add(questionLabel);

        for (String option : options) {
            Button button = new Button();
            button.setText(option);
            button.addActionListener(this);
            add(button);
            buttonList.add(button);
        }
        bar.setValue(20);
        add(bar) ;
        fill();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
                }
                else{
                    b.setBackground(new Color(252, 139, 154));
                }
                jb.setOpaque(true);
                jb.repaint();
                jb.revalidate();
            }
            returnToServer("FALSE");
        }
    }


    public List<String> generateListAndshuffle(String item1,String item2, String item3, String item4){
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
        out.println(string);
    }


}




