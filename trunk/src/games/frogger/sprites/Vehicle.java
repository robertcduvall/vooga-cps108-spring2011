package games.frogger.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class Vehicle extends Sprite {
	
	private static final long serialVersionUID = 1L;

	public Vehicle(BufferedImage image, int x, int y) {
		super(image, x, y);
	}
	
	@Override
	public Double getArbitraryRotate() {
		return 180.0;
	}
	
}
