package games.missileDefense.sprites;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class Text extends Sprite
{
	/**
	 * basically placeholder sprite. Doesn't really interact
	 * @param image
	 * @param x
	 * @param y
	 */
	public Text(BufferedImage image, int x, int y)
	{
		super(image, x - image.getWidth()/2, y - image.getHeight()/2);
        this.setAngle(Direction.NORTH.getAngle());
	}
}
