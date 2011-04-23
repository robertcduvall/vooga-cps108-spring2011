package vooga.physics.newtonianProperties;

import java.awt.Point;

import vooga.physics.util.IPhysicsToggle;

/**
 * Interface which defines objects that can rotate. See PhyiscsRotateC.
 * @author Nathan Klug
 *
 */
public interface INewtonianRotate extends IPhysicsToggle{

    public double getMass();
    
    public double getRotationalVelocity();

    public void setRotationalVelocity(double newVelocity);

    public Point getCenter();
    
}
