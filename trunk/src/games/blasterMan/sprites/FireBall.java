package games.blasterMan.sprites;

import java.awt.image.BufferedImage;
import vooga.util.buildable.components.IComponent;

@SuppressWarnings("serial")
public class FireBall extends SkillSprite{
	public FireBall() {
		super();
	}

	public FireBall( BufferedImage image, double x, double y, IComponent... comps) {
		super( image, x, y, comps);
	}

	public FireBall(BufferedImage image, double x, double y) {
		super( image, x, y);
	}

	public FireBall( BufferedImage image) {
		super( image);
	}

	public FireBall( double x, double y) {
		super( x, y);
	}
}
