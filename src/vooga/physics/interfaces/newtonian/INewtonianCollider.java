package vooga.physics.interfaces.newtonian;

import java.awt.Point;
import vooga.physics.interfaces.IPhysicsCustomCollide;
import vooga.util.math.Angle;

/**
 * Provides extra methods which allow for more information about the other
 * object being collided with.
 * 
 * @author Nathan Klug
 * 
 */
public interface INewtonianCollider extends IPhysicsCustomCollide {

    public void collisionOccurred(INewtonianMovable otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);

    public void collisionOccurred(INewtonianPhysical otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
