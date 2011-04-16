package vooga.physics.interfaces;

/**
 * Interface that should be implemented for objects using friction. See PhysicsFrictionC
 * @author Nathan Klug
 *
 */
public interface IPhysicsFriction extends IPhysicsToggle{
    
    public double getCoefficientOfFriction();
}
