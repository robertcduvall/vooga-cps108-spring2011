package vooga.physics.interfaces.newtonian;

import java.awt.Point;
import vooga.physics.interfaces.IPhysicsCustomCollide;
import vooga.util.math.Angle;

public interface INewtonianCollider extends IPhysicsCustomCollide{

    public void collisionOccurred(INewtonianMovable otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);

    public void collisionOccurred(INewtonianPhysical otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution);
}
