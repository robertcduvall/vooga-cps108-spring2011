package vooga.physics.interfaces;

import java.awt.Point;
import vooga.physics.util.Velocity;

/**
 * Interface which defines methods needed by objects which exert a vector field from a point.
 * 
 * @author Nathan Klug
 *
 */
public interface IPointField extends IPhysicsToggle{
    
    public static double constant = 0;
    
    public double getPointMagnitude();

    public Point getCenter();

    public Velocity getVelocity();

    public void setVelocity(Velocity spriteVelocity);
    
}
