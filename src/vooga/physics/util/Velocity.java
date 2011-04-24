package vooga.physics.util;

import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * MathVector class for Velocities. Has been made a separate class from MathVector
 * so Velocities are kept separate. It should not be a valid operation to add a
 * Velocity to a Force.
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
    
    public Velocity subtractVector(Velocity otherVector) {
        super.subtractVector(otherVector);
        return this;
    }
    
    public Velocity scalarMultiply(double scalar){
        super.scalarMultiply(scalar);
        return this;
    }
}
