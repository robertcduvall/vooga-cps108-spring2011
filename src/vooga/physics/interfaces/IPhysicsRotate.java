package vooga.physics.interfaces;

import java.awt.Point;

/**
 * Interface which defines objects that can rotate. See PhyiscsRotateC.
 * @author Nathan Klug
 *
 */
public interface IPhysicsRotate extends IPhysicsToggle{

    public double getMass();
    
    public double getRotationalVelocity();

    public void setRotationalVelocity(double newVelocity);

    public Point getCenter();
    
}
