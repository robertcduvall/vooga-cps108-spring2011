package vooga.physics.util;

import vooga.physics.newtonianInterfaces.INewtonianMovable;
import vooga.physics.newtonianInterfaces.INewtonianPhysical;
import vooga.util.math.Angle;

/**
 * Implementation of a force which is proportional to mass, such as gravity.
 * @author Nathan Klug
 *
 */
public class MassProportionalForce extends Force {

    public MassProportionalForce(double magnitude, Angle angle) {
        super(magnitude, angle);
    }
    
    @Override
    public MassProportionalForce addVector(Force otherVector) {
        super.addVector(otherVector);
        return this;
    }
    
    @Override
    public MassProportionalForce scalarMultiply(double scalar) {
        super.scalarMultiply(scalar);
        return this;
    }
    
    /**
     * Applies an external force to an IPhysics object which is proportional to it's mass (effectively ignoring its mass)
     * 
     * @param physicalObject
     * @param force
     * @param elapsedTime
     */
    @Override
    public void applyForce(INewtonianPhysical physicalObject, long elapsedTime) {
        Velocity deltaVelocity = new Velocity(this.getMagnitude() * elapsedTime, this.getAngle());
        Velocity spriteVelocity = physicalObject.getVelocity();
        spriteVelocity.addVector(deltaVelocity);
        physicalObject.setVelocity(spriteVelocity);
    }
    
    /**
     * Applies an external force to a sprite which is proportional to it's mass (effectively ignoring its mass)
     * 
     * @param sprite
     * @param force
     * @param elapsedTime
     */
    public void applyForce(INewtonianMovable object, long elapsedTime) {
        Velocity deltaVelocity = new Velocity(this.getMagnitude() * elapsedTime, this.getAngle());
        Velocity objectVelocity = object.getVelocity();
        objectVelocity.addVector(deltaVelocity);
        object.setVelocity(objectVelocity);
    }
    

}
