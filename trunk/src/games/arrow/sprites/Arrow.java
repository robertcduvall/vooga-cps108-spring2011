package games.arrow.sprites;

import java.awt.image.BufferedImage;

import vooga.collisions.shapes.ShapeFactory;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.AccelerationC;
import vooga.sprites.spritebuilder.components.basic.PermAccelerationC;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;

public class Arrow extends Sprite {

	@Override
	public Double getAngle() {
		return super.getAngle()-85;
	}


	public Arrow(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        this.height = 25;
        this.width = 50;
        this.addComponents(new PermAccelerationC(0.0, .0001), new CollisionPolygonC(new CollisionPolygon(this.getCenterX(), this.getCenterY(), 3 , this.getHeight())));
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
