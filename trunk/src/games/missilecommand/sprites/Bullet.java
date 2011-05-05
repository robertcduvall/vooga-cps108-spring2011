package games.missilecommand.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * A sprite that represents a bullet fired by the turret.
 * @author Alex Lee (hl69)
 */
public class Bullet extends Sprite
{

	public Bullet(BufferedImage image, int x, int y)
	{
		super(image, x, y);
		this.addComponent(new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 4))));;
	}

	public void render(Graphics2D g, int x, int y)
	{
	    super.render(g, x, y);
	}
}
