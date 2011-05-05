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