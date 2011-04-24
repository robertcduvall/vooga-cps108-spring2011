package vooga.util.math;

import java.awt.Point;

/**
 * Vector class which handles basic vector math. Physical vectors such as Force
 * and Velocity extend this class.
 * 
 * @author Anne Weng
 * @author Nathan Klug
 * 
 */
// USES STANDARD EAST = 0 DEGREES, COUNTERCLOCKWISE
public class MathVector {

    private double myMagnitude;
    private Angle myAngle;

    /**
     * Constructor for defining a MathVector by magnitude and angle.
     * 
     * @param magnitude
     * @param angle
     */
    public MathVector(double magnitude, Angle angle) {
        myMagnitude = magnitude;
        myAngle = angle;
    }

    /**
     * Constructor for defining a MathVector by X and Y components.
     * 
     * @param xComponent
     * @param yComponent
     */
    public MathVector(double xComponent, double yComponent) {
        this(getMagnitude(xComponent, yComponent), getAngle(xComponent, yComponent));
    }
    
    /**
     * Constructor for defining a MathVector by parallel and perpendicular components
     * along a given angle.
     * @param parallelComponent
     * @param perpComponent
     * @param angle
     */
    public MathVector(double parallelComponent, double perpComponent, Angle angle){
        this(parallelComponent*angle.cos() + perpComponent*angle.cos(), 
                parallelComponent*angle.sin() + perpComponent*angle.sin());
        
    }
    
    /**
     * Constructor for a vector defined from point1 to point2.
     * @param point1
     * @param point2
     */
    public MathVector(Point point1, Point point2) {
	this(point2.getX()-point1.getX(), point2.getY()-point1.getY());
    }
    

    /**
     * Returns the magnitude of the MathVector.
     * 
     * @return
     */
    public double getMagnitude() {
        return myMagnitude;
    }

    /**
     * Returns the angle of the MathVector.
     * 
     * @return
     */
    public Angle getAngle() {
        return myAngle;
    }
    
    /**
     * Sets myAngle to be newAngle.
     * @param newAngle
     */
    public MathVector setAngle(Angle newAngle) {
	myAngle = newAngle;
	return this;
    }
    
    /**
     * Negates the angle of the vector so it points 180 degrees opposite.
     */
    public MathVector negateAngle(){
        myAngle = myAngle.setNegativeAngle();
        return this;
    }
    
    /**
     * Subtracts from this vector the other vector.
     * @param otherVector
     */
    public MathVector subtractVector(MathVector otherVector){
        MathVector negateVector = otherVector.negateAngle();
        addVector(negateVector);
        return this;
    }

    /**
     * Adds the current MathVector to another MathVector and returns the result.
     * 
     * @param otherVector
     * @return
     */
    protected MathVector addVector(MathVector otherVector) {
        double xComponent = this.getXComponent() + otherVector.getXComponent();
        double yComponent = this.getYComponent() + otherVector.getYComponent();
        myMagnitude = getMagnitude(xComponent, yComponent);
        myAngle = getAngle(xComponent, yComponent);
        return this;
    }

    /**
     * Returns the angle of a vector defined by its X and Y components.
     * 
     * @param xComponent
     * @param yComponent
     * @return
     */
    private static Angle getAngle(double xComponent, double yComponent) {
        return new Angle(Math.atan2(yComponent, xComponent));
    }

    /**
     * Returns the magnitude of a vector defined by its X and Y components.
     * 
     * @param xComponent
     * @param yComponent
     * @return
     */
    private static double getMagnitude(double xComponent, double yComponent) {
        return Math.sqrt(Math.pow(xComponent, 2) + Math.pow(yComponent, 2));
    }

    /**
     * Returns the X component of the vector.
     * 
     * @return
     */
    public double getXComponent() {
        return myMagnitude * myAngle.cos();
    }

    /**
     * Returns the Y component of the vector.
     * 
     * @return
     */
    public double getYComponent() {
        return myMagnitude * myAngle.sin();
    }

    /**
     * Multiplies the vector's magnitude by a scalar and returns the resulting
     * vector.
     * 
     * @param scalar
     * @return
     */
    protected MathVector scalarMultiply(double scalar) {
        myMagnitude *= scalar;
        return this;
    }

    /**
     * Calculates the dot product of this vector with another vector and returns the scalar
     * result as a double. Dot product is defined as the product of the x components of the
     * two vectors plus the product of the y components of the two vectors.
     * @param otherVector
     * @return
     */
    public double dotProduct(MathVector otherVector) {
        return getXComponent() * otherVector.getXComponent() + getYComponent() * otherVector.getYComponent();
    }
    

    /**
     * Calculates the component of the current vector that is parallel to a given angle by
     * taking the dot product with a unit vector of that angle.
     * @param angle
     * @return
     */
    public double getParallelComponent(Angle angle) {
        return dotProduct(new MathVector(1, angle));
    }

    /**
     * Calculates the component of the current vector that is perpendicular to a given angle
     * by taking the dot product with a unit vector of the perpendicular angle.
     * @param angle
     * @return
     */
    public double getPerpComponent(Angle angle) {
        angle.setRadians(angle.getRadians() + Math.PI / 2);
        return dotProduct(new MathVector(1, angle));
    }
    
    /**
     * Calculates and returns the small angle [0, pi] between two vectors.
     * @param otherVector
     * @return
     */
    public Angle getVectorAngle(MathVector otherVector) {
	double radians = myAngle.getRadians()-otherVector.getAngle().getRadians();
	return new Angle(radians % Math.PI);
    }
}
