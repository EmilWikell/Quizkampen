package GUI;

import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    JLabel condition = new JLabel();

    public WinPanel(Object informationPackFromServer) {
        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(1, 1));

        condition.setText(informationPackFromServer.toString());
        condition.setHorizontalAlignment(SwingConstants.CENTER);
        condition.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,30));
        condition.setBackground(new Color (103, 51, 150));
        condition.setOpaque(true);
        condition.setForeground(Color.WHITE);
        this.add(condition);
    }
}
