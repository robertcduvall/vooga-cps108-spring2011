package vooga.sprites.spritebuilder.components.basic;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.predefined.basic.DoubleC;

public class PermAccelerationC extends DoubleC implements ISpriteUpdater {
	
Double dY;
	
	public PermAccelerationC(Double dx, Double dy) {
		super(dx);
		dY = dy;
	}

	@Override
	public void update(Sprite s, long elapsedTime) {
		s.addSpeed(myDouble*elapsedTime, dY*elapsedTime);
	}

}
