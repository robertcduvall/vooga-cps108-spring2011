package vooga.sprites.spritebuilder.components.basic;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.predefined.basic.DoubleC;
import vooga.util.buildable.components.predefined.movement.HeadingC;
import vooga.util.math.LineMath;

public class SpriteVelocityC extends HeadingC implements ISpriteUpdater{

	protected double myMagnitude;
	
	
	public SpriteVelocityC(Double mag, Double dir){
		super(dir);
		myMagnitude = mag;
		
		
	}
	
	public SpriteVelocityC(){
		this(0.0,90.0);
	}
	
	@Override
	protected int compareTo(BasicComponent o) {
		return ((Double)this.getMagnitude()).compareTo((Double)((SpriteVelocityC) o).getMagnitude());
	}

	private double getMagnitude() {
		return myMagnitude;
	}

	@Override
	protected Object[] getFieldValues() {
		return new Object[]{myMagnitude, myAngle};
	}

	@Override
	protected void setFieldValues(Object... fields) {
		myMagnitude = (Double) fields[0];
		myAngle = (Double) fields[1];
	}

	/**
	 * adjusts to screen x/y
	 */
	@Override
	public void update(Sprite s, long elapsedTime) {
//		System.out.println(this.getXComponent() + "," + this.getYComponent());
		s.move(this.getXComponent()*elapsedTime, -this.getYComponent()*elapsedTime);
	}

	public double getXComponent() {
		return myMagnitude*Math.cos(Math.toRadians(myAngle));
	}

	public double getYComponent() {
		return myMagnitude*Math.sin(Math.toRadians(myAngle));
	}

	public double setByComponents(double vx, double vy) {
		if (vx!= 0 || vy != 0)
			this.setAngle(LineMath.findDirection(vx, vy));
		System.out.println(myAngle);
		return myMagnitude = LineMath.calcMagnitude(vx,vy);
	}

	public double addByComponents(double dVx, double dVy) {
		return this.setByComponents(getXComponent()+dVx, getYComponent()+dVy);
	}


}
