package games.pong.sprites;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;

/**
 * 
 * 
 * @author shun fan
 */
@SuppressWarnings("serial")
public class Ball extends Sprite
{

    
    public Ball(BufferedImage image, int x, int y)
    {
        super(image,x,y);
        System.out.println("ballcreated!");
        setX(x);
        setY(y);
        setSpeed(.75, 0);
        
        //addComponent(new CollisionCircleC(getCenterPoint(), getWidth()/2));
    }
 
    
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