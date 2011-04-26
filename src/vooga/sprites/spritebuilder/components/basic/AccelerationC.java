package vooga.sprites.spritebuilder.components.basic;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.predefined.basic.DoubleC;


public class AccelerationC extends DoubleC implements ISpriteUpdater{

	
	public AccelerationC(Double s) {
		super(s);
	}

	@Override
	public void update(Sprite s, long elapsedTime) {
		s.accelerate(myDouble, s.getAngle());
	}

}
