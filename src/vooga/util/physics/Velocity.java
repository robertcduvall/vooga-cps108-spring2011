package vooga.util.physics;

import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * MathVector class for Velocities.
 * 
 * @author Anne Weng
 *
 */
public class Velocity extends MathVector {

    public Velocity(double magnitude, Angle angle) {
        super(magnitude, angle);
    }
    
    public Velocity(double xComponent, double yComponent) {
	super(xComponent, yComponent);
    }
    
    public Velocity(double parallelComponent, double perpComponent, Angle angle){
        super(parallelComponent, perpComponent, angle);
    }

    public Velocity addVector(Velocity otherVector) {
        super.addVector(otherVector);
        return this;
    }
    
    public Velocity scalarMultiply(double scalar){
        super.scalarMultiply(scalar);
        return this;
    }
}
