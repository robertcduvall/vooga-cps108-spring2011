package games.mariobros.sprites;

import games.mariobros.MarioBros;

import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * 
 * @author Ethan Goh
 * 
 */
@SuppressWarnings("serial")
public class GoalBlock extends Sprite
{
	public GoalBlock(BufferedImage image, int x, int y)
	{
		super(image, 1 + x * (2 + image.getWidth()), 1 + y
				* (2 + image.getHeight()));

		setImage(MarioBros.imageLoader.getImage("goal"));

		this.setAngle(Direction.NORTH.getAngle());
	}
}
