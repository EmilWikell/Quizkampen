package GUI;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    JLabel condition = new JLabel();

    public WinPanel() {
        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(1, 1));

        condition.setText("You Won!");
        this.add(condition);
    }
}
