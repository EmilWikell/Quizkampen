package GUI;


import ClientLogic.ClientTest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class CategoryPanel extends JPanel implements ActionListener {


    ClientTest ct;
    JLabel questionLabel = new JLabel();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();

    private String messageToChoseCategory;
    private String category1;
    private String category2;
    private String category3;
    private String category4;




    public CategoryPanel(String messageToChoseCategory, String category1, String category2, String category3, String category4) throws IOException, ClassNotFoundException {
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;

        setPreferredSize(new Dimension(400, 900));
        this.setPreferredSize(new Dimension(600, 1000));
        this.setLayout(new GridLayout(5, 1));


        questionLabel.setText(messageToChoseCategory);
        this.add(questionLabel);

        button1.setText(category1);
        button1.addActionListener(this);
        this.add(button1);

        button2.setText(category2);
        button2.addActionListener(this);
        this.add(button2);

        button3.setText(category3);
        button3.addActionListener(this);
        this.add(button3);

        button4.setText(category4);
        button4.addActionListener(this);
        this.add(button4);

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        //  OBS! Tanke??? Att vi skapar en metod likt ct.returnToServer() men som är separat och returnar next Category.
        //  Vet inte hur vi ska göra exakt men vi måste bestämma hur vi sätter nästa kategori
        //  vi måste hitta en sätt att stänga ner och ta emot nästa panel --> protokol ändå?
        if(e.getSource()== button1){
            System.out.println("You selected category: " + category1);
            button1.setBackground(Color.GREEN);
            ct.returnToServer(category1);
        }
        if(e.getSource()== button2){
            System.out.println("You selected category: " + category2);
            button2.setBackground(Color.GREEN);
            ct.returnToServer(category2);
        }
        if(e.getSource()== button3){
            System.out.println("You selected category: " + category3);
            button3.setBackground(Color.GREEN);
            ct.returnToServer(category3);
        }
        if(e.getSource()== button4){
            System.out.println("You selected category: " + category4);
            button4.setBackground(Color.GREEN);
            ct.returnToServer(category4);
        }

    }


}

