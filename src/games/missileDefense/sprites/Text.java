package games.missileDefense.sprites;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class Text extends Sprite
{
	public Text(BufferedImage image, int x, int y)
	{
		super(image, x - image.getWidth()/2, y - image.getHeight()/2);
        this.setAngle(Direction.NORTH.getAngle());
	}
}
