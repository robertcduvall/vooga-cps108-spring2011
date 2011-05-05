package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.IComponent;

public class AbstractPaddle extends Sprite {
	private int health = 20;

	public AbstractPaddle() {
		super();
	}

	public AbstractPaddle(BufferedImage image, double x, double y, IComponent... comps) {
		super(image, x, y, comps);
	}

	public AbstractPaddle(BufferedImage image, double x, double y) {
		super(image, x, y);
	}

	public AbstractPaddle(BufferedImage image) {
		super(image);
	}

	public AbstractPaddle(double x, double y) {
		super(x, y);
	}
	
	@Override
    public Double getArbitraryRotate() {
            return 0D;
    }

}
