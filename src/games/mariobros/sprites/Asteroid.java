package games.mariobros.sprites;

import games.mariobros.MarioBros;

import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

@SuppressWarnings("serial")
public class Asteroid extends Sprite
{
	public Asteroid(BufferedImage image, int x, int y)
	{
		super(image, 1 + x * (2 + image.getWidth()), 1 + y
				* (2 + image.getHeight()));

		setImage(MarioBros.imageLoader.getImage("asteroid"));

		this.setAngle(Direction.NORTH.getAngle());
		this.addComponent(new CollisionPolygonC(new CollisionPolygon(
				ShapeFactory.makePolygonFromImage(
						ImageUtil.resize(image, width, height), 2))));

	}
	
	public void randomizeVelocityTowardsScreen()
	{
		Random r = new Random();
		setHorizontalSpeed(r.nextDouble()/8.0);
		setVerticalSpeed(r.nextDouble()/8.0);
	}

}
