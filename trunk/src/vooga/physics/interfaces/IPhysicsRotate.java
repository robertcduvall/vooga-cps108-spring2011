package vooga.physics.interfaces;

/**
 * Interface which defines objects that can rotate. See PhyiscsRotateC.
 * @author Nathan Klug
 *
 */
public interface IPhysicsRotate {

    public double getMass();
    
    public double getRotationalVelocity();

    public void setRotationalVelocity(double newVelocity);
    
    public boolean isOn();
    
    /**
     * Turns the physics FOR THIS PARTICULAR OBJECT on or off based on the parameter.
     */
    public void setPhysicsOnOff(boolean isOn);
}
