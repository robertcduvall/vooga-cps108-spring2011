package vooga.leveleditor.gui;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawingBoard extends JPanel
{

    public static final int LAYER_GREENSCREEN = -100;
    public static final int LAYER_BACKGROUND = 0;
    public static final int LAYER_SPRITES = 100;
    public static final int LAYER_DRAG = 500;

    private Palette palette;
    private ImageIcon test;

    private JLayeredPane layers;

    public DrawingBoard()
    {
        /*
         * Use a BorderLayout so that we can easily fill all available space.
         */
        this.setLayout(new BorderLayout());
        
        /*
         * Set up test image
         */
        test =  new ImageIcon("src/vooga/leveleditor/images/space_ship.png");
        DragableImage image = new DragableImage(test);
        

       
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
        int width = bgimage.getWidth();
        int height = bgimage.getHeight();

        /*
         * Construct the greenscreen. This shows the user where the end of the
         * level is, and it extends 100 pixels each side past the boundaries
         * of the background image.
         */
        JLabel greenscreen = new JLabel();
        greenscreen.setBackground(Color.GREEN);
        greenscreen.setOpaque(true);
        greenscreen.setBounds(0, 0, width+200, height+200);

        /*
         * Now we have enough information to create the JLayeredPane.
         */
        layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(width+200, height+200));
        //layers.add(image);
        //layers.moveToFront(image);
        //layers.setLayer(image, 1);
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
         * Add the greenscreen and the background.
         */
        layers.add(greenscreen, LAYER_GREENSCREEN);
        JLabel background = new JLabel(new ImageIcon(bgimage));
        background.setOpaque(true);
        background.setBounds(100, 100, width, height);
        layers.add(background, LAYER_BACKGROUND);
    }
  
    

}
