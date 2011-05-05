package games.missileDefense.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import games.missileDefense.MissileDefense;
import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.sprites.spritebuilder.components.collisions.CollisionShapeC;
import vooga.util.buildable.components.predefined.basic.HealthC;

public class City extends Sprite
{
	/**
	 * build a city!
	 * @param image
	 * @param x
	 * @param y
	 * @param health
	 */
	public City(BufferedImage image, int x, int y, double health)
	{
		super(image, x, y);
		this.setAngle(Direction.NORTH.getAngle());
		this.addComponents(new HealthC(health), new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 2))));
	}
	/**
	 * when the city is hit with something that could damage it (a missile)
	 */
	public void damage()
	{
		this.getComponent(HealthC.class).decrease(1.0);
		{
			if(this.getComponent(HealthC.class).isDead())
			{
				this.setActive(false);
			}
		}
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
        g.setColor(Color.RED);
		this.getCollisionShape().render(g);
	}
}
