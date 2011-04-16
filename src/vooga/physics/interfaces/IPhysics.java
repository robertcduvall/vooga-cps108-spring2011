package vooga.physics.interfaces;

import java.awt.Point;

import vooga.physics.util.Velocity;

/**
 * Interface which defines basic methods that all objects using physics should implement.
 * See PhysicsC.
 * 
 * @author Nathan Klug
 *
 */
public interface IPhysics extends IMovable, IPhysicsToggle{
    
    public double getMass();
    
    public Point getCenter();

}
