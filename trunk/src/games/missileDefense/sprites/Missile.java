package games.missileDefense.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.physics.BasicPhysicsC;

public class Missile extends Sprite
{

	
	public Missile(BufferedImage image, int x, int y, double horizSpeed)
	{
		super(image, x, y);

		this.addComponents(new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height)))), new BasicPhysicsC(300.0, true));
		this.setAngle(Direction.SOUTH.getAngle());
		this.setHorizontalSpeed(horizSpeed);
		
	}

	public void render(Graphics2D g, int x, int y)
	{
		AffineTransform aTransform = new AffineTransform();
		aTransform.translate((int) this.getX() +width/2, 
				(int) this.getY()+height/2);
		aTransform.rotate(Math.toRadians(this.getAngle()+90));

		aTransform.translate((int) -width/2, 
				(int) -height/2);
		if (this.getHorizontalSpeed() < 0) g.drawImage(ImageUtil.flip(ImageUtil.resize(image, width, height)),aTransform,null);
		else g.drawImage(ImageUtil.resize(image, width, height),aTransform,null);
		super.renderComponents(g, x, y);
		g.setColor(Color.BLUE);
		this.getCollisionShape().render(g);
	}

}
