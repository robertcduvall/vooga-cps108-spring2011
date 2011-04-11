package vooga.physics.interfaces;

import vooga.physics.calculators.PhysicsCalculator;
import vooga.util.physics.Velocity;

/**
 * Interface which defines basic methods that all objects using physics should implement.
 * @author Nathan Klug
 *
 */
public interface IPhysics {
    
    public PhysicsCalculator getCalculator();
    
    public double getMass();
    
    public Velocity getVelocity();
    
    public void setVelocity(Velocity newVelocity);
    
    public boolean isOn();
    
    public void setPhysicsOnOff();

}
