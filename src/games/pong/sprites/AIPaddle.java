package games.pong.sprites;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.PongTargetC;

public class AIPaddle extends Sprite{
	public AIPaddle(Sprite ball) {
		
		//set behavior
		PongTargetC trackBall = new PongTargetC();
		trackBall.setTarget(ball);
		addComponent(trackBall);
	}
}
