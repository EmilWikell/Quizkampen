package GUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton{
    Color color ;

    public Button() {

        //setBorderPainted(false);//
        //setContentAreaFilled(false);//
        //setFocusPainted(false);//

        setPreferredSize(new Dimension(100,100)) ;
        setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,12)) ;
        setBorder(BorderFactory.createLineBorder(
                new Color (224,215,208),5,false)) ;
        setBackground(new Color (103, 51, 150)) ;
        setForeground(Color.WHITE) ;
        setOpaque(true) ;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                if (button.isEnabled()) {
                    color = getBackground() ;
                    setBackground(new Color (201, 171, 232));
                    button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)) ;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                if (button.isEnabled()) {
                    setBackground(color);
                    //setBackground(new Color (103, 51, 150)) ;
                    button.setBorder(BorderFactory.createLineBorder(
                            new Color (224,215,208),2,true)) ;
                }
            }
        });
    }
}
