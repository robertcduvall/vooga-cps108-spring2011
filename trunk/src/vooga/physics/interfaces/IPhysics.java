package vooga.physics.interfaces;

import java.awt.Point;

import vooga.physics.calculators.PhysicsCalculator;
import vooga.physics.util.Velocity;

/**
 * Interface which defines basic methods that all objects using physics should implement.
 * @author Nathan Klug
 *
 */
public interface IPhysics {
    
    /**
     * Returns the calculator currently being used by the object. If it hasn't yet been defined,
     * add the following code to this method to define it using the current defaults:
     * 
     * if (myCalculator == null)
     *      myCalculator = PhysicsCalculator.getBestCalcForInterface(this);
     * return myCalculator;
     * @return
     */
    public PhysicsCalculator getCalculator();
    
    public double getMass();
    
    public Point getCenter();
    
    public Velocity getVelocity();
    
    public void setVelocity(Velocity newVelocity);
    
    public boolean isOn();
    
    /**
     * Turns the physics FOR THIS PARTICULAR OBJECT on or off based on the parameter.
     */
    public void setPhysicsOnOff(boolean isOn);

}
