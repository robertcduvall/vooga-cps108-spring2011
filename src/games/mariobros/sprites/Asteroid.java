package games.mariobros.sprites;

import games.mariobros.MarioBros;

import java.awt.image.BufferedImage;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Asteroid extends Sprite
{
	public Asteroid(BufferedImage image, int x, int y)
	{
		super(image, 1 + x * (2 + image.getWidth()), 1 + y
				* (2 + image.getHeight()));

		setImage(MarioBros.imageLoader.getImage("asteroid"));

		this.setAngle(Direction.NORTH.getAngle());
	}
	
	public void randomizeVelocityTowardsScreen()
	{
		
	}

}
