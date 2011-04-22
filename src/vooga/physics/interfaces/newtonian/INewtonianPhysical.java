package vooga.physics.interfaces.newtonian;

import java.awt.Point;

import vooga.physics.interfaces.IPhysicsToggle;

/**
 * Interface which defines basic methods that all objects using physics should implement.
 * See PhysicsC.
 * 
 * @author Nathan Klug
 *
 */
public interface INewtonianPhysical extends INewtonianMovable, IPhysicsToggle{
    
    public double getMass();
    
    public Point getCenter();

}
