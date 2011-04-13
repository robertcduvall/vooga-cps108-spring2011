package vooga.physics.interfaces;

/**
 * Interface that should be implemented for objects using friction. See PhysicsFrictionC
 * @author Nathan Klug
 *
 */
public interface IPhysicsFriction {
    
    public double getCoefficientOfFriction();
    
    public boolean isOn();
    
    /**
     * Turns the physics FOR THIS PARTICULAR OBJECT on or off based on the parameter.
     */
    public void setPhysicsOnOff(boolean isOn);
}
