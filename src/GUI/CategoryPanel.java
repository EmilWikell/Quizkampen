package GUI;


import ClientLogic.ClientTest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

public class CategoryPanel extends JPanel implements ActionListener {



    JLabel questionLabel ;
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();

    private String messageToChoseCategory;
    private String category1;
    private String category2;
    private String category3;
    private String category4;
    private PrintWriter out;

    public CategoryPanel(String messageToChoseCategory, String category1, String category2, String category3, String category4, PrintWriter out) throws IOException, ClassNotFoundException {
        this.messageToChoseCategory = messageToChoseCategory;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
        this.out = out;

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(5, 1));

        questionLabel = new JLabel("<html><div style='text-align: center;'>" +
                messageToChoseCategory + "</div></html>") ;
        questionLabel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,30)) ;
        questionLabel.setBackground(Color.lightGray.brighter());
        questionLabel.setForeground(new Color (103, 51, 150)) ;
        questionLabel.setOpaque(true);
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
        JButton jb = new JButton();
        jb = (JButton) e.getSource();
        returnToServer(jb.getText());

    }

    private void returnToServer(String string) {
        System.out.println("we sent back info to server");
        out.println(string);
    }


}

