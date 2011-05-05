package games.watcher.sprites;

import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.levelsRefactored.LevelManager;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A child that you must watch after
 * 
 * @author Conrad
 */
@SuppressWarnings("serial")
public class Children extends Sprite
{
	public int condition = 0;
	private EventManager myEventManager;
    /**
     * Creates a child object and places it within the playfield
     * @param image The image to use for the child.
     */
    public Children(BufferedImage image, int x, int y)
    {
        super(image,x,y);
        setX(x);
        setY(y);
        setAngle(-90);
        
    }
    
    /**
     * @return The radius of the sphere encapsulating the child.
     */
    public double getRadius() {return getWidth()/2D;}
    
    /**
     * Signifies that the child has wondered off screen - the player loses points for this
     */
    public void hitBottomBorder()
    {
        this.setActive(false);
        myEventManager.fireEvent(this, "Game.LowerChildCount");
    }
    
    /**
     * Signifies that the child has wondered off screen - the player loses points for this
     */
    public void hitTopBorder()
    {
        this.setActive(false);
        myEventManager.fireEvent(this, "Game.LowerChildCount");
    }
    
    /**
     * If the child hits the wall that it came from, nothing should happen
     */
    public void hitLeftBorder()
    {}
    
    /**
     * Signifies that the child has successfully crossed the plain without dying and is therefore a win
     */
    public void hitRightBorder()
    {//For this option we do nothing
    	myEventManager.fireEvent(this, "Game.RaiseChildCount");
    }   
}
