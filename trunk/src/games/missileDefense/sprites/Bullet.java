package games.missileDefense.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionCircleC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

/**
 * Bullet fired from the gun
 * @author johnegan
 *
 */
public class Bullet extends Sprite
{

	public Bullet(BufferedImage image, int x, int y)
	{
		super(image, x, y);
		this.addComponent(new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 4))));;
	}

	/*
	 * this renders a frame around it
	 * (non-Javadoc)
	 * @see vooga.sprites.improvedsprites.Sprite#render(java.awt.Graphics2D, int, int)
	 */
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
        g.setColor(Color.YELLOW);
		this.getCollisionShape().render(g);
	}
}