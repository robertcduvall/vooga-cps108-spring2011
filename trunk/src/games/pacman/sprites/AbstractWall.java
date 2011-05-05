package games.pacman.sprites;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.golden.gamedev.util.ImageUtil;

import vooga.sprites.improvedsprites.Sprite;

public abstract class AbstractWall extends Sprite{

	public AbstractWall(BufferedImage image, int x, int y) {
		super(image,x,y);
	}
	

	@Override
	public Double getArbitraryRotate() {
		return 0D;
	}

	
	
}
