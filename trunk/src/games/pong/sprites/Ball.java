package games.pong.sprites;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;

/**
 * 
 * 
 * @author fanny
 */
@SuppressWarnings("serial")
public class Ball extends Sprite
{
    /**
     * The ball's speed, in pixels per ms.
     */
    public static final Double BALL_SPEED = 0.4D;
    
    /**
     * Creates a ball moving straight up and sitting on 
     * top of the point (x,y)
     * 
     * @param image The image to use for the ball.
     */
    public Ball(BufferedImage image, int x, int y)
    {
        super(image,x,y);
        setX(x);
        setY(y);
        setSpeed(.1, .1);
    }
    
    public void bounceUp()
    {
        if (getVerticalSpeed() > 0)
            setVerticalSpeed(-getVerticalSpeed());
    }
    
    /**
     * Bounce the ball downwards from an obstacle above
     * the ball (this exists as a separate method so
     * that multiple collisions don't cancel out).
     */
    public void bounceDown()
    {
        if (getVerticalSpeed() < 0)
            setVerticalSpeed(-getVerticalSpeed());
    }
    
    /**
     * Bounce the ball to the left.
     */
    public void bounceLeft()
    {
        if (getHorizontalSpeed() > 0)
            setHorizontalSpeed(-getHorizontalSpeed());
    }
    
    /**
     * Bounce the ball to the right.
     */
    public void bounceRight()
    {
        if (getHorizontalSpeed() < 0)
            setHorizontalSpeed(-getHorizontalSpeed());
    }
   
}


/*package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class Ball extends Sprite{

	public Ball(BufferedImage image, double x, double y) {
		super(image, x, y);
	}


}
*/