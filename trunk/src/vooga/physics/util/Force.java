package vooga.physics.util;

import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * MathVector class for Forces. Has been made a separate class from MathVector
 * so Forces are kept separate. It should not be a valid operation to add a
 * Velocity to a Force.
 * 
 * @author Anne Weng
 * 
 */
public class Force extends MathVector {

    public Force(double magnitude, Angle angle) {
        super(magnitude, angle);
    }

    public Force(double xComponent, double yComponent) {
        super(xComponent, yComponent);
    }

    public Force(double parallelComponent, double perpComponent, Angle angle) {
        super(parallelComponent, perpComponent, angle);
    }

    public Force addVector(Force otherVector) {
        super.addVector(otherVector);
        return this;
    }
    
    public Force subtractVector(Force otherVector){
        super.subtractVector(otherVector);
        return this;
    }

    public Force scalarMultiply(double scalar) {
        super.scalarMultiply(scalar);
        return this;
    }

    /**
     * Applies an external force to an IPhysics object using the Impulse
     * Momentum Theorem. <br>
     * <br>
     * Source: <a
     * href="http://en.wikipedia.org/wiki/Impulse_momentum_theorem">Wikipedia
     * </a>
     * 
     * @param physicalObject
     * @param force
     * @param elapsedTime
     */
    public void applyForce(INewtonianPhysical physicalObject, long elapsedTime) {

        Velocity deltaVelocity = new Velocity(this.getMagnitude() * elapsedTime / physicalObject.getMass(),
                this.getAngle());
        Velocity spriteVelocity = physicalObject.getVelocity();
        spriteVelocity.addVector(deltaVelocity);
        physicalObject.setVelocity(spriteVelocity);
    }
}
