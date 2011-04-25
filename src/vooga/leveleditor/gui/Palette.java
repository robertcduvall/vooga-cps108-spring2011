package vooga.leveleditor.gui;

import java.awt.*;
import javax.swing.*;

/**
 * A palette of sprite buttons. To be used with the DrawingBoard.
 */
public class Palette extends JPanel
{

    public static final Dimension DEFAULT_SIZE = new Dimension(220, 600);
    
    private Viewport pane;
    
    public Palette(Viewport pane)
    {
        super();
        this.pane = pane;
        
        this.setPreferredSize(DEFAULT_SIZE);
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        this.add(new SpriteButton(pane));
        this.add(new SpriteButton(pane));
        this.add(new SpriteButton(pane));
        this.add(new SpriteButton(pane));
        this.add(new SpriteButton(pane));
    }

}
