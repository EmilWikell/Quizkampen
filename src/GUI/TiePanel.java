package GUI;

import javax.swing.*;
import java.awt.*;

public class TiePanel extends JPanel {

    JLabel condition = new JLabel();

    public TiePanel() {
        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(1, 1));

        condition.setText("Game Tied");
        this.add(condition);
    }
}
