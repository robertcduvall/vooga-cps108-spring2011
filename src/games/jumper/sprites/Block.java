package games.jumper.sprites;

import games.jumper.Jumper;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
/**
 * The Door Sprite
 * 
 * @author Charlie Hatcher
 */
@SuppressWarnings("serial")
public class Block extends Sprite{
	/**
	 * Establishes the Block with the given image, x coordinate, and y coordinate.
	 * Sets the angle of the sprite to North
	 * @param image
	 * @param x
	 * @param y
	 */
	public Block(BufferedImage image, int x, int y){
		super(image, 1 + x * (2 + image.getWidth()), 1 + y * (2 + image.getHeight()));
		this.setImage(Jumper.myImageLoader.getImage("block"));
		this.setAngle(Direction.NORTH.getAngle());
		this.setX(x);
		this.setY(y);
	}
}