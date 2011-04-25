package vooga.sprites.improvedsprites.interfaces;

public interface IRotation {

	/**
	 * Sets the heading angle to the input double. Calculated based on cartesian standards
	 * @param angle = IN DEGREES
	 */
	public abstract void setAngle(double angle);

	/**
	 * Returns the heading of this object in degrees. Based on cartesian standards
	 * @return Angle in DEGREES
	 */
	public abstract double getAngle();

	/**
	 * Rotates this object a certain number of DEGREES and returns the new heading
	 * @param dAngle = in DEGREES
	 * @return new heading
	 */
	public abstract double rotate(double dAngle);

}