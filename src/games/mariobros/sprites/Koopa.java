package games.mariobros.sprites;

import games.mariobros.MarioBros;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class Koopa extends Sprite
{
	public Koopa(BufferedImage image, int x, int y, int c)
	{
		super(image, 1 + x * (2 + image.getWidth()), 1 + y
				* (2 + image.getHeight()));

		setImage(MarioBros.imgLoader.getImage("koopa", c));
		
		this.setAngle(Direction.NORTH.getAngle());
	}
}
