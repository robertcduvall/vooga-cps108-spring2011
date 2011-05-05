package games.asteroids;

import java.awt.image.BufferedImage;
import vooga.sprites.improvedsprites.Sprite;


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
    }

    public void explode ()
    {
        // TODO Auto-generated method stub
    }
}
