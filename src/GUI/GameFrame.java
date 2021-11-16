package GUI;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    CategoryPanel cp = new CategoryPanel();

    GameFrame(){
        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        add(cp);
        pack();
    }



    public static void main(String[] args) {

        GameFrame gf = new GameFrame();

    }
}
