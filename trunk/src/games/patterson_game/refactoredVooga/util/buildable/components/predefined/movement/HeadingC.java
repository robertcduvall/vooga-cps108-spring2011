package games.patterson_game.refactoredVooga.util.buildable.components.predefined.movement;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.IRotation;
import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.DoubleC;

public class HeadingC extends BasicComponent implements IRotation{

	
	
	protected Double myAngle;

	public HeadingC(Double s) {
		myAngle = s;
	}
	
	public HeadingC() {
		this(0.0);
	}

	@Override
	public Double getAngle(){
		return myAngle;
	}

	@Override
	public Double setAngle(double angle) {
		return myAngle = angle;
	}

	@Override
	public Double rotate(double dAngle) {
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
