/**
 * 
 */
package games.tao_rpsallstars.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;
/**
 * @author Kevin
 *
 */
public class PaperThrow extends Sprite{
    public PaperThrow (BufferedImage image, int x, int y)
    {
        super(image,x,y);
        setX(x - getWidth()/2);
        setY(y - getHeight());
        setAngle(+90);
        setAbsoluteSpeed(20);
    }
}
