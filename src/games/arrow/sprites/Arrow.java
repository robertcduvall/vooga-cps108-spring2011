package games.arrow.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.AccelerationC;
import vooga.sprites.spritebuilder.components.basic.PermAccelerationC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

public class Arrow extends GoodSprite {

	@Override
	public Double getAngle() {
		return super.getAngle();
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
		this.getCollisionShape().render(g);
	}
	
	public Arrow(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        this.height = 25;
        this.width = 50;
        this.addComponents(new PermAccelerationC(0.0, .0001), new CollisionPolygonC(new CollisionPolygon(ShapeFactory.makePolygonFromImage(ImageUtil.resize(image, width,height), 5))));
    }
    
	
    @Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}


	public void destroy ()
    {
    	this.setActive(false);
    }
	
}
