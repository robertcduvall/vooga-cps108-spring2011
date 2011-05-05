package vooga.leveleditor.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import org.w3c.dom.Element;

/**
 * A button that holds a sprite.
 * 
 */
@SuppressWarnings("serial")
public class SpriteButton extends JButton
{

    private ImageIcon myIcon;
    private Viewport myPane;
    @SuppressWarnings("unused")
	private String myImageLocation;
    private Element spriteProperties;

    public SpriteButton(Viewport pane, Element spritedata)
    {
    	myPane = pane;
    	spriteProperties = spritedata;
    	String path = spriteProperties.getElementsByTagName("image").item(0).getTextContent();
    	//setImageLocation();
        //myIcon = new ImageIcon(myImageLocation);
    	myIcon = new ImageIcon(path);
        this.setIcon(myIcon);
        this.setPreferredSize(new Dimension(100, 100));
        this.addActionListener(new ClickAction());
    }

    private class ClickAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
        	/*
        	 * Need to create a sprite button factory
        	 */
            DraggableImage s = new DraggableImage(myPane, spriteProperties, true);
            myPane.addMouseMotionListener(s);
            myPane.addImage(s);
        }
       

    }
    
    /*
     * This method may be used by the parser in order to set the image for
     * each button as they are being created from the file. If needed, we will
     * replace this with a SpriteButtonFactory
     */
    protected void setImageLocation(){
    	myImageLocation = "src/vooga/leveleditor/images/space_ship.png";
    }

}
