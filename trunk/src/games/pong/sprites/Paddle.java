package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;

public class Paddle extends Sprite {

	public Paddle() {
		super();
	}

	public Paddle(BufferedImage image, double x, double y, IComponent... comps) {
		super(image, x, y, comps);
	}

	public Paddle(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public Paddle(BufferedImage image) {
		super(image);
	}

	public Paddle(double x, double y) {
		super(x, y);
	}

}
