package vooga.physics.newtonianInterfaces;

import java.awt.Point;

import vooga.physics.IPhysicsToggle;

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
