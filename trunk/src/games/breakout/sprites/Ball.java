package games.breakout.sprites;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Ball extends Sprite
{
    private static final Double BALL_SPEED = 0.4D;
    
    
    public Ball(BufferedImage image, int x, int y)
    {
        super(image,x,y);
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAngle(-90);
        setAbsoluteSpeed(BALL_SPEED);
    }
    
    public double getRadius() {return getWidth()/2D;}
    
    public void bounceUp()
    {
        if (getVerticalSpeed() > 0)
            setVerticalSpeed(-getVerticalSpeed());
    }
    
    public void bounceDown()
    {
        if (getVerticalSpeed() < 0)
            setVerticalSpeed(-getVerticalSpeed());
    }
    
    public void bounceLeft()
    {
        if (getHorizontalSpeed() > 0)
            setHorizontalSpeed(-getHorizontalSpeed());
    }
    
    public void bounceRight()
    {
        if (getHorizontalSpeed() < 0)
            setHorizontalSpeed(-getHorizontalSpeed());
    }
}
