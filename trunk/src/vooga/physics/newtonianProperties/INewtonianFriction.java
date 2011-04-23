package vooga.physics.newtonianProperties;

import vooga.physics.util.IPhysicsToggle;

/**
 * Interface that should be implemented for objects using friction. See PhysicsFrictionC
 * @author Nathan Klug
 *
 */
public interface INewtonianFriction extends IPhysicsToggle{
    
    public double getCoefficientOfFriction();
}
