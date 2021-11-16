package GUI;


import java.awt.*;
import javax.swing.*;

public class CategoryPanel extends JPanel {


    CategoryPanel() {
        setPreferredSize(new Dimension(400, 900));
        setLayout(new GridLayout(5, 1));
        setVisible(true);

        JLabel jb = new JLabel("Hello, Chose category: ");
        jb.setPreferredSize(new Dimension(390, 100));
        jb.setHorizontalAlignment(JLabel.CENTER);
        jb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        jb.setForeground(Color.black);
        add(jb);

        // Entertainment
        // Geography
        // Sports
        // History
        // It-Computers
        JButton category1 = new JButton("Entertainment");
        add(category1);

        JButton category2 = new JButton("Geography");
        add(category2);

        JButton category3 = new JButton("It-Computers");
        add(category3);

        JButton category4 = new JButton("History");
        add(category4);

        // JButton category5 = new JButton("Sports");
        // add(category5);

    }



}

