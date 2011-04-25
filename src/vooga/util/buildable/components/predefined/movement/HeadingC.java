package vooga.util.buildable.components.predefined.movement;

import vooga.sprites.improvedsprites.interfaces.IRotation;
import vooga.util.buildable.components.predefined.basic.DoubleC;

public class HeadingC extends DoubleC implements IRotation{

	public HeadingC(Double s) {
		super(s);
	}
	
	public HeadingC() {
		super(0.0);
	}

	@Override
	public double getAngle(){
		return super.getDouble();
	}

	@Override
	public void setAngle(double angle) {
		myDouble = angle;
	}

	@Override
	public double rotate(double dAngle) {
		
		return myDouble = (myDouble + dAngle)%360;
	}

}
