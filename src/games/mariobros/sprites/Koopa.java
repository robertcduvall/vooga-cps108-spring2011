package games.mariobros.sprites;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;

public class Koopa extends Sprite
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
    public Koopa(BufferedImage image, int x, int y)
    {
        super(image,x,y);
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAngle(-90);
        setAbsoluteSpeed(BALL_SPEED);
    }
    
    /**
     * @return The radius of the ball.
     */
    public double getRadius() {return getWidth()/2D;}
    
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
