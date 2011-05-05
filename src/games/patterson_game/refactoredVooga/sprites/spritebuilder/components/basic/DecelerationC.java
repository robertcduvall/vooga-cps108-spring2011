package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;

public class DecelerationC extends AccelerationC {

	public DecelerationC(Double s) {
		super(-s);
	}

	@Override
	public void update(Sprite s, long elapsedTime) {
		if (s.getAbsoluteSpeed() >= -myDouble)
			super.update(s, elapsedTime);
		else
			s.setSpeed(0, 0);
		
	}
	
	

}
