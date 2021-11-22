package GUI;

import javax.swing.*;
import java.awt.*;

public class WaitingPanel extends JPanel {
    public WaitingPanel(){

        //JLabel waiting = new JLabel();
        ImageIcon icon = new ImageIcon("src/loading.gif") ;
        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT) ;
        ImageIcon icon1 = new ImageIcon(image) ;
        JLabel iconLabel = new JLabel(icon1);
        setPreferredSize(new Dimension(400, 700));
        this.setLayout(new GridLayout(1, 1));

        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(iconLabel) ;
    }
}
