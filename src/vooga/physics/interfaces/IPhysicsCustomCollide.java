package vooga.physics.interfaces;

import java.awt.Point;
import vooga.util.math.Angle;

/**
 * @author Nathan Klug
 *
 */
public interface IPhysicsCustomCollide{

    public void collisionOccurred(Object otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
