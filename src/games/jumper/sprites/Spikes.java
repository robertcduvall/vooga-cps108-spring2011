package games.jumper.sprites;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
/**
 * The spikes sprite
 * 
 * @author Charlie Hatcher
 */
@SuppressWarnings("serial")
public class Spikes extends Sprite{
	
	/**
	 * Establishes the spikes with the given image, x coordinate, and y coordinate.
	 * Sets the angle of the sprite to North
	 * @param image
	 * @param x
	 * @param y
	 */
	public Spikes(BufferedImage image, int x, int y){
		this.setImage(image);
		this.setX(x);
		this.setY(y);
		this.setAngle(Direction.NORTH.getAngle());
	}
}