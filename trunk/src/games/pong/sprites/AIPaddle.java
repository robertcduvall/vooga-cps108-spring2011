package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.PongTargetC;

public class AIPaddle extends Sprite{

	public AIPaddle(BufferedImage image, double x, double y, Sprite ball) {
		super(image, x, y);
		PongTargetC trackBall = new PongTargetC();
		trackBall.setTarget(ball);
		addComponent(trackBall);
	}
}
