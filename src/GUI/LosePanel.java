package GUI;

import javax.swing.*;
import java.awt.*;

public class LosePanel extends JPanel {

    JLabel condition = new JLabel();

    public LosePanel() {
        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(1, 1));

        condition.setText("You lost");
        this.add(condition);
    }
}
