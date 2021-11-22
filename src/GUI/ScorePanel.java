package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ScorePanel extends JPanel {

    JLabel headLabel = new JLabel();
    JLabel empty = new JLabel();
    JLabel myTot = new JLabel();
    JLabel myRound = new JLabel();
    JLabel oppTot = new JLabel();
    JLabel oppRound = new JLabel();
    JLabel lblMyTot = new JLabel();
    JLabel lblMyRound = new JLabel();
    JLabel lblOppTot = new JLabel();
    JLabel lblOppRound = new JLabel();

    private String myName;
    private String oppName;
    private String category1;
    private String category2;
    private String category3;
    private String category4;


    public ScorePanel(String myName,String oppName, String category1, String category2, String category3, String category4) throws IOException, ClassNotFoundException {
        this.myName = myName;
        this.oppName = oppName;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(5, 2));

        headLabel.setText("Score");
        headLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(headLabel);
        empty.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(empty);

        lblMyTot.setHorizontalAlignment(SwingConstants.CENTER);
        lblMyTot.setText(myName + "  total:");
        this.add(lblMyTot);
        myTot.setText(this.category1);
        myTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(myTot);

        lblMyRound.setHorizontalAlignment(SwingConstants.CENTER);
        lblMyRound.setText(myName + "  round:");
        this.add(lblMyRound);
        myRound.setText(this.category2);
        myRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(myRound);

        lblOppTot.setHorizontalAlignment(SwingConstants.CENTER);
        lblOppTot.setText(oppName + "  total:");
        this.add(lblOppTot);
        oppTot.setText(this.category3);
        oppTot.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oppTot);

        lblOppRound.setHorizontalAlignment(SwingConstants.CENTER);
        lblOppRound.setText(oppName + "  round:");
        this.add(lblOppRound);
        oppRound.setText(this.category4);
        oppRound.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(oppRound);


        this.setVisible(true);
    }
}
