package vooga.physics.interfaces;

import java.awt.Point;
import vooga.util.math.Angle;

/**
 * Interface which should be implemented if one would like to override the default processing
 * of the collision.
 * 
 * @author Nathan Klug
 *
 */
public interface IPhysicsCustomCollide{

    public void collisionOccurred(Object otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
