package vooga.leveleditor.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A button that holds a sprite.
 */
public class SpriteButton extends JButton
{

    private ImageIcon myIcon;

    public SpriteButton()
    {
        myIcon = new ImageIcon("leveleditor/images/space_ship.png");
        this.setIcon(myIcon);
        this.setPreferredSize(new Dimension(100, 100));
        this.addActionListener(new ClickAction());
    }

    private class ClickAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            DragableImage s = new DragableImage(myIcon);
        }

    }

}
