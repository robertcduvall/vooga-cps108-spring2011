package vooga.physics.collisionBehavior;

import java.awt.Point;

import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * 
 * Visitor interface for collisions. Fixes Java's multiple dispatch problem we had going on.
 * 
 * @author Anne Weng
 *
 */
public interface CollisionVisitor {

    Velocity visitFirst(CollisionVisitor first, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution);
    Velocity visitNext(EmptyCollisionBehavior empty, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution);
    Velocity visitNext(FrictionCollisionBehavior friction, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution);
    Velocity visitNext(MovableCollisionBehavior movable, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution);
    Velocity visitNext(PhysicalCollisionBehavior physical, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution);
}
