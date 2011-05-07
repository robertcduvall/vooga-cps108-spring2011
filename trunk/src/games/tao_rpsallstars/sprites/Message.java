package games.tao_rpsallstars.sprites;

import java.awt.image.BufferedImage;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A message saying "You win!" or "You lose!"
 * 
 * @author Misha
 */
@SuppressWarnings("serial")
public class Message extends Sprite
{
    public Message(BufferedImage image, int x, int y)
    {
        super(image, x - image.getWidth()/2, y - image.getHeight()/2);
        this.setAngle(Direction.NORTH.getAngle());
    }
}
