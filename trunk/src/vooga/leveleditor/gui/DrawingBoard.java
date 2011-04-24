package vooga.leveleditor.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The main rendering and drawing area of the editor. It is composed of the
 * palette, which is a panel of buttons with pictures of sprites on them, and
 * the viewport, which is where the level is actually rendered and where the
 * user can place new sprites.
 * @author Alex Lee
 * @author Charlie Hatcher
 */
public class DrawingBoard extends JPanel
{

    private SwingGUI owner;

    private Palette palette;

    private Viewport layers;

    private JLabel statusbar;

    public DrawingBoard(SwingGUI owner)
    {
        /*
         * User a BorderLayout so that we can easily fill all available space
         * with the viewport.
         */
        super(new BorderLayout());

        /*
         * Associate this with the controller.
         */
        this.owner = owner;

        /*
         * Load the background image so that we know how big the drawing board
         * should be.
         */
        BufferedImage bgimage = null;
        try
        {
            bgimage = ImageIO.read(new File("src/vooga/leveleditor/images/space.jpg"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        /*
         * Now we have enough information to create the JLayeredPane. It is
         * placed in the center so that it will take all available space.
         */
        layers = new Viewport(this, bgimage);
        JScrollPane layersHolder = new JScrollPane(layers);
        this.add(layersHolder, BorderLayout.CENTER);

        /*
         * Create the palette on the left.
         */
        palette = new Palette(layers);
        JScrollPane paletteHolder = new JScrollPane(palette,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paletteHolder.setPreferredSize(new Dimension(240, 600));
        add(paletteHolder, BorderLayout.WEST);

        /*
         * Create the statusbar.
         */
        this.statusbar = new StatusBar();
    }

    protected void setStatusBar(String message)
    {
        statusbar.setText(message);
    }

}
