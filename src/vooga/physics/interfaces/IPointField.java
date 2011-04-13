package vooga.physics.interfaces;

import java.awt.Point;
import vooga.physics.util.Velocity;

/**
 * Interface which defines methods needed by objects which exert a vector field from a point.
 * 
 * @author Nathan Klug
 *
 */
public interface IPointField{
    
    public double CONSTANT = 0;
    
    public double getPointMagnitude();
    
    public boolean isOn();
    
    /**
     * Turns the physics FOR THIS PARTICULAR OBJECT on or off based on the parameter.
     */
    public void setPhysicsOnOff(boolean isOn);

    public Point getCenter();

    public Velocity getVelocity();

    public void setVelocity(Velocity spriteVelocity);
    
}
