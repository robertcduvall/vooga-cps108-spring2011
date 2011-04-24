package vooga.gui.util;

import java.awt.Dimension; 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Button class that is used extensively by Panes.
 *@author Dave Crowe, David Colon-Smith
 */
public class VoogaButton {
	//State
    private BufferedImage image;
    @SuppressWarnings("unused")
	private Dimension imageSizes;
    private Dimension position;
    private String title;
    
    //Constructors
    /**
     * Creates a VoogaButton with blank values.
     */
    public VoogaButton()
    {
        this(null,"",new Dimension(0,0));
    }
    
    /**
     * Creates a button object.
     * @param image the buttons image; defines the height and width of our button
     * @param title the "title" of the button (make something up)
     * @param position the position, use "new Dimension(x, y)" to create one.
     */
    public VoogaButton(BufferedImage image, String title, Dimension position)
    {
        this.title = title;
        this.position = position;
        setImage(image);
    }
    
    //Useful methods
    /**
     * Returns true if the button is hit by an x and y value.
     * @param mouseX
     * @param mouseY
     * @return true if the button is hit, false if not.
     */
    public boolean hitBy(double mouseX, double mouseY) {
        int xRange = position.width + image.getWidth();
        int yRange = position.height + image.getHeight();
        if(mouseX > position.width && mouseX < xRange){
        	if(mouseY > position.height && mouseY <  yRange){
                return true;
            }
        }
            
        return false;
    }

    /**
     * Renders this object to the specified graphics2d
     * @param g
     */
    public void render(Graphics2D g) { 
    	if (image!=null)
    		g.drawImage(image, position.width, position.height, null);
    }
    
    /**
     * @return this Button's Title
     */
    public String getTitle(){
    	return title;
    }
    
    /**
     * Change the position for this button.
     * @param d
     */
    public void setPosition(Dimension d){
    	position=d;
    }

    /**
     * sets our image to specified image
     * @param image
     */
    public void setImage(BufferedImage image) {
        // TODO see if it needs anything else
        this.image = image;
        imageSizes = new Dimension(image.getWidth(), image.getHeight());
    }
    
    public BufferedImage getImage() {
		return image;
	}

}