package vooga.sprites.spritebuilder.components.basic;

import vooga.sprites.improvedsprites.Sprite;

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
