package vooga.leveleditor.gui;

import java.awt.*;
import javax.swing.*;

public class Palette extends JPanel
{

    public static final Dimension DEFAULT_SIZE = new Dimension(200, 500);

    public Palette()
    {
        super();

        this.setPreferredSize(DEFAULT_SIZE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(new SpriteButton());
        this.add(new SpriteButton());
        this.add(new SpriteButton());
        this.add(new SpriteButton());
        this.add(new SpriteButton());
    }

}
