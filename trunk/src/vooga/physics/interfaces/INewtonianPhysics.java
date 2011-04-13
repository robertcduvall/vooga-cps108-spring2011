package vooga.physics.interfaces;

/**
 * DEPRECATED. Split between IPhysicsFriction and IPhysicsRotate
 * 
 * Interface which extends IPhysics, defining more specific behavior that a
 * sprite can have.
 * 
 * @author Nathan Klug
 * 
 */
public interface INewtonianPhysics extends IPhysics {

    public double getRotationalVelocity();

    public void setRotationalVelocity(double newVelocity);

    public double getCoefficientOfFriction();
}
