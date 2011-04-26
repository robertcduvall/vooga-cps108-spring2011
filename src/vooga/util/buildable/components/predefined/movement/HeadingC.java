package vooga.util.buildable.components.predefined.movement;

import vooga.sprites.improvedsprites.interfaces.IRotation;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.predefined.basic.DoubleC;

public class HeadingC extends BasicComponent implements IRotation{

	
	
	protected Double myAngle;

	public HeadingC(Double s) {
		myAngle = s;
	}
	
	public HeadingC() {
		this(0.0);
	}

	@Override
	public double getAngle(){
		return myAngle;
	}

	@Override
	public void setAngle(double angle) {
		myAngle = angle;
	}

	@Override
	public double rotate(double dAngle) {
		return myAngle = (myAngle + dAngle)%360;
	}

	@Override
	protected int compareTo(BasicComponent o) {
		return ((Double)this.getAngle()).compareTo((Double)((HeadingC) o).getAngle());
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[]{myAngle};
	}

	@Override
	protected void setFieldValues(Object... fields) {
		myAngle = (Double) fields[0];
	}

}
