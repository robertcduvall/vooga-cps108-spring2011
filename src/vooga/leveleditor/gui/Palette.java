package vooga.leveleditor.gui;

import java.awt.*;
import javax.swing.*;

public class Palette extends JPanel
{

    public static final Dimension DEFAULT_SIZE = new Dimension(200, 500);

    private JLayeredPane myPane;
    public Palette(JLayeredPane pane)
    {
        super();
        myPane = pane;
        this.setPreferredSize(DEFAULT_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(new SpriteButton(myPane));
        this.add(new SpriteButton(myPane));
        this.add(new SpriteButton(myPane));
    }

}
