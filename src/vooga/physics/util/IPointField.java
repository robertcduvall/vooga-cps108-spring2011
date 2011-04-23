package vooga.physics.util;

import java.awt.Point;

/**
 * Interface which defines methods needed by objects which exert a vector field
 * from a point. See PointFieldC
 * 
 * @author Nathan Klug
 * 
 */
public interface IPointField extends IPhysicsToggle {

    public static double constant = 0;

    public double getPointMagnitude();

    public Point getCenter();

    public Velocity getVelocity();

    public void setVelocity(Velocity spriteVelocity);

}
