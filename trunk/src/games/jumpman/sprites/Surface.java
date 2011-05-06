package games.jumpman.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class Surface extends Sprite {
	
	private static final long serialVersionUID = 1L;

	public Surface(BufferedImage image, int x, int y) {
		super(image, x, y);
	}
	
	@Override
	public Double getArbitraryRotate() {
		return 0.0;
	}
}
