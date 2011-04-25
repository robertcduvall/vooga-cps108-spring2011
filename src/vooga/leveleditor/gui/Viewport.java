package vooga.leveleditor.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

/**
 * Renders and draws the level. Allows the user to interact with sprites that
 * are on it.
 * @author Alex Lee
 * @author Charlie Hatcher
 */
public class Viewport extends JLayeredPane
{

    public static final int LAYER_GREENSCREEN = -100;
    public static final int LAYER_BACKGROUND = 0;
    public static final int LAYER_SPRITES = 100;
    public static final int LAYER_DRAG = 500;

    private int x,y;
    private DrawingBoard owner;
    private ArrayList<DraggableImage> mySprites;

    public Viewport(DrawingBoard owner)
    {
        super();

        mySprites = new ArrayList<DraggableImage>();
        this.owner = owner;
        addMouseMotionListener(new MouseTracker());
        addMouseListener(new MouseClick());
    }

    public void load(BufferedImage bgimage)
    {
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
         * Add the greenscreen and the background.
         */
        this.add(greenscreen, LAYER_GREENSCREEN);
        JLabel background = new JLabel(new ImageIcon(bgimage));
        background.setOpaque(true);
        background.setBounds(100, 100, width, height);
        this.add(background, LAYER_BACKGROUND);
    }

    void addImage(DraggableImage i){
        mySprites.add(i);
    }

    /**
     * Follows the mouse and updates the status bar with the mouse coordinates.
     */
    private class MouseTracker implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent e)
        {
            x = e.getX();
            y = e.getY();
            owner.setStatusBar(String.format("(%d, %d)", x, y));
        }

        public void mouseDragged(MouseEvent e)
        {
            return;
        }
    }
    private class MouseClick implements MouseListener{

        /**
         * If the mouse is hovering over the image on the viewport and is clicked,
         * set up that image to become draggable.
         */
        @Override
        public void mouseClicked(MouseEvent arg0) {
            /*if(!arg0.isControlDown()){
				moveSelectedSprite();
			}
			if(arg0.isControlDown()){
				editSelectedSprite();
			}*/
            for(int i=0; i<mySprites.size();i++){
                if(!arg0.isControlDown()){
                    if(mySprites.get(i).moveIfSelected(x, y));
                }
                if(arg0.isControlDown()){
                    mySprites.get(i).setSpriteProperties(x, y);
                }
            }
        }

        /**
         * Brings up a new internal frame that serves as a popup to allow the user to
         * edit the sprites properties in a text file.
         * 
         * Currently unused
         */
        /*
		private void editSelectedSprite() {
			for(int i=0; i<mySprites.size();i++){
				mySprites.get(i).setSpriteProperties(x, y);
			}
		}
         */
        /*
         * FIXME: iterate through the for loop backwards not sure if this is needed
         * anymore
         * 
         * currently unused
         */
        /*
		private void moveSelectedSprite() {
			if(mySprites.size()>0){
				for(DraggableImage i: mySprites){
					if(i.moveIfSelected(x, y));
				}
			}
		}
         */
        /**
         * the rest of these methods are unneeded, but come standard with the
         * MouseListener interface
         */
        @Override
        public void mouseEntered(MouseEvent arg0) {
            return;
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            return;
        }

        @Override
        public void mousePressed(MouseEvent arg0) {

            return;
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            return;
        }

    }

}
