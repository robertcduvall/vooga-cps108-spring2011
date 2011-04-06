package sprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class TestSprite extends Sprite {

	private static final long serialVersionUID = 1L;

	public TestSprite() {
		
	}
	
	public TestSprite(BufferedImage image, double x, double y) {
		super (image, x, y);
	}
	
}
