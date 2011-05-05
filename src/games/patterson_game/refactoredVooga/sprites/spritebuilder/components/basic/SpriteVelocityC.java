package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ISpriteUpdater;
import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic.DoubleC;
import games.patterson_game.refactoredVooga.util.buildable.components.predefined.movement.HeadingC;
import games.patterson_game.refactoredVooga.util.math.LineMath;

public class SpriteVelocityC extends HeadingC implements ISpriteUpdater{

	protected Double myMagnitude;
	protected Double oldAngle;

	@Override
	public Double setAngle(double angle) {
		super.setAngle(angle);
		return oldAngle = myAngle;
	}

	@Override
	public Double rotate(double dAngle) {
		 super.rotate(dAngle);
		 return oldAngle = myAngle;
	}

	public SpriteVelocityC(Double mag, Double dir){
		super(dir);
		myMagnitude = mag;
		oldAngle = myAngle;
		
		
	}
	
	public SpriteVelocityC(){
		this(0.0,0.0);
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
		oldAngle = myAngle = (Double) fields[1];
	}

	/**
	 * adjusts to screen x/y
	 */
	@Override
	public void update(Sprite s, long elapsedTime) {
//		System.out.println(this.getXComponent() + "," + this.getYComponent());
		s.move(this.getXComponent()*elapsedTime, this.getYComponent()*elapsedTime);
	}

	public double getXComponent() {
		return myMagnitude*Math.cos(-Math.toRadians(myAngle));
	}

	public double getYComponent() {
		return myMagnitude*Math.sin(Math.toRadians(myAngle));
	}

	public double setByComponents(double vx, double vy) {
		if ((myAngle =LineMath.findDirection(vx, vy)) == null)
			myAngle = oldAngle;
		else setAngle(LineMath.findDirection(vx, vy));
//		System.out.println(myAngle);
		return myMagnitude = LineMath.calcMagnitude(vx,vy);
	}

	public double addByComponents(double dVx, double dVy) {
		return this.setByComponents(getXComponent()+dVx, getYComponent()+dVy);
	}



}
