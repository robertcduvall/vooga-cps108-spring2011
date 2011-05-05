package games.asteroids;

import java.awt.image.BufferedImage;

import vooga.collisions.shapes.ShapeFactory;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;


public class Asteroid extends Sprite
{
    @Override
	public Double getArbitraryRotate() {
		return 0.0;
	}

	private static final long serialVersionUID = 1L;
    
    public Asteroid(BufferedImage image, int x, int y)
    {
        super(image, x, y);
        this.addComponent(new CollisionPolygonC(ShapeFactory.makePolygonFromImage(this.getImage(),3)));
    }

    public void explode ()
    {
    }
}
