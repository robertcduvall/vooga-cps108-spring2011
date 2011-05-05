package games.arrow.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;


public class GoodSprite extends Sprite {

	@Override
	public Double getArbitraryRotate() {
		return 0.0;
	}

	public GoodSprite() {
		super();
	}

	public GoodSprite(BufferedImage image, double x, double y,
			IComponent... comps) {
		super(image, x, y, comps);
	}

	public GoodSprite(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public GoodSprite(BufferedImage image) {
		super(image);
	}

	public GoodSprite(double x, double y, IComponent... comps) {
		super(x, y, comps);
	}

	public GoodSprite(double x, double y) {
		super(x, y);
	}
	
	

}
