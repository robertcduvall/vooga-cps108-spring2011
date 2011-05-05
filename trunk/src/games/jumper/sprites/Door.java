package games.jumper.sprites;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
/**
 * The Door sprite
 * 
 * @author Charlie Hatcher
 */
@SuppressWarnings("serial")
public class Door extends Sprite{
	/**
	 * Establishes the Door with the given image, x coordinate, and y coordinate.
	 * Sets the angle of the sprite to North
	 * @param image
	 * @param x
	 * @param y
	 */
	public Door(BufferedImage image, int x, int y){
		this.setImage(image);
		this.setX(x);
		this.setY(y);
		this.setAngle(Direction.NORTH.getAngle());
	}
}