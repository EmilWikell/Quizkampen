package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ScorePanel extends JPanel {

    JLabel headLabel = new JLabel();
    JLabel empty = new JLabel();
    JLabel myTot = new JLabel();
    JLabel myRound = new JLabel();
    JLabel oppTot = new JLabel();
    JLabel oppRound = new JLabel();
    JLabel lblMyTot = new JLabel("My Total:");
    JLabel lblMyRound = new JLabel("My Round:");
    JLabel lblOppTot = new JLabel("Opponents Total:");
    JLabel lblOppRound = new JLabel("Opponents Round:");

    private String messageToChoseCategory;
    private String category1;
    private String category2;
    private String category3;
    private String category4;


    public ScorePanel(String messageToChoseCategory, String category1, String category2, String category3, String category4) throws IOException, ClassNotFoundException {
        this.messageToChoseCategory = messageToChoseCategory;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(5, 2));

        headLabel.setText(this.messageToChoseCategory);
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(headLabel);
        empty.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(empty);

        lblMyTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblMyTot);
        myTot.setText(this.category1);
        myTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(myTot);

        lblMyRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblMyRound);
        myRound.setText(this.category2);
        myRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(myRound);

        lblOppTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblOppTot);
        oppTot.setText(this.category3);
        oppTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oppTot);

        lblOppRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblOppRound);
        oppRound.setText(this.category4);
        oppRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oppRound);


        this.setVisible(true);
    }
}
