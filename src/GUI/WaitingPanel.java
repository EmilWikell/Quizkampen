package GUI;

import javax.swing.*;
import java.awt.*;

public class WaitingPanel extends JPanel {
    public WaitingPanel(){

        JLabel waiting = new JLabel();

        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(1, 1));

        waiting.setText("Waiting");
        waiting.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(waiting);
    }
}
