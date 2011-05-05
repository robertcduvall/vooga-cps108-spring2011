package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.DoubleC;


public class AccelerationC extends DoubleC implements ISpriteUpdater{

	
	public AccelerationC(Double s) {
		super(s);
	}

	@Override
	public void update(Sprite s, long elapsedTime) {
		s.accelerate(myDouble, s.getAngle());
	}

}
