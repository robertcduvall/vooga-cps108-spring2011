package games.watcher.sprites;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.sprites.improvedsprites.Sprite;

/**
 * The walker example, a monster represents the crazy unbeatableness of the game
 * 
 * @author Conrad
 */
@SuppressWarnings("serial")
public class Monster extends Children
{
	/**
     * The ball's speed, in pixels per ms.
     */
    public Random random = new Random();
    public double[] viableSpeeds = {.25,.3,.5,.75,1,1.25,1.5,1.75};
    
    /**
     * Creates a chicken object and places it within the playfield
     * @param image The image to use for the ball.
     */
    public Monster(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        setAngle(90);
        Random rand = new Random();
        setAbsoluteSpeed(rand.nextDouble());
    }
    
    /**
     * Bounce the ball upwards from an obstacle
     * below the ball.
     */
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
