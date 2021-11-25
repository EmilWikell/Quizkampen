package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class CategoryPanel extends JPanel implements ActionListener {



    JLabel categoryLabel;
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

    public CategoryPanel(String messageToChoseCategory, String category1, String category2, String category3, String category4, PrintWriter out) {
        this.messageToChoseCategory = messageToChoseCategory;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
        this.out = out;

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(5, 1));

        categoryLabel = new JLabel("<html><div style='text-align: center;'>" +
                messageToChoseCategory + "</div></html>") ;
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,30)) ;
        categoryLabel.setBackground(Color.lightGray.brighter());
        categoryLabel.setForeground(new Color (103, 51, 150)) ;
        categoryLabel.setOpaque(true);
        this.add(categoryLabel);

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
        JButton jb = new JButton();
        jb = (JButton) e.getSource();
        returnToServer(jb.getText());

    }

    private void returnToServer(String string) {
        out.println(string);
    }


}

