package vooga.physics.newtonianProperties;

import vooga.physics.util.Velocity;

/**
 * Interface which defines if an object has a velocity, and provides a way to
 * interface with said object. See VelocityC for an implementation.
 * 
 * @author Nathan Klug
 * 
 */
public interface INewtonianMovable {

    /**
     * NOTE: this should get the velocity in standard mathematical (cartesian)
     * coordinates. This means that up and down are backwards from typical CS
     * notation.
     */
    public Velocity getVelocity();

    /**
     * NOTE: this should set the velocity that is given by standard mathematical
     * (cartesian) coordinates. This means that up and down are backwards from
     * typical CS notation.
     */
    public void setVelocity(Velocity newVelocity);
}
