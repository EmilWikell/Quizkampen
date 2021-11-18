package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ScorePanel extends JPanel {

    JLabel headLabel = new JLabel();
    JLabel myTot = new JLabel();
    JLabel myRound = new JLabel();
    JLabel oppTot = new JLabel();
    JLabel oppRound = new JLabel();
    JButton ready = new JButton("Ready");

    private String messageToChoseCategory;
    private String category1;
    private String category2;
    private String category3;
    private String category4;
    private PrintWriter out;

    public ScorePanel(String messageToChoseCategory, String category1, String category2, String category3, String category4, PrintWriter out) throws IOException, ClassNotFoundException {
        this.messageToChoseCategory = messageToChoseCategory;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
        this.out = out;

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(6, 1));

        headLabel.setText(this.messageToChoseCategory);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(headLabel);

        myTot.setText(this.category1);
        myTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(myTot);

        myRound.setText(this.category2);
        myRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(myRound);

        oppTot.setText(this.category3);
        oppTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oppTot);

        oppRound.setText(this.category4);
        oppRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oppRound);

        ready.addActionListener(e -> out.println("ready"));
        this.add(ready);

        this.setVisible(true);
    }
}
