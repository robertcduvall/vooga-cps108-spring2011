package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.AbstractBehavior;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Exactly what the class name says. Defines collision behavior which does nothing. To be used in Vooga
 * in the case where a physics component does not define collision behavior.
 * 
 * @author Nathan Klug
 *
 */
public class EmptyCollisionBehavior extends AbstractBehavior implements CollisionVisitor {

    /**
     * Returns the change in the current object's velocity, given a collision with another object's collision behavior.
     * 
     * @param otherCollisionBehavior the collision behavior of the other object
     * @param angleOfImpact
     * @param pointOfImpact
     * @param coefficientOfRestitution
     * @return the change in velocity that occurs as a result
     */
    public Velocity collisionToVelocityChange(EmptyCollisionBehavior otherCollisionBehavior, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return new Velocity(0, new Angle());

    }

    @Override
    public Velocity visitFirst(CollisionVisitor first, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return first.visitNext(this, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }

    @Override
    public Velocity visitNext(EmptyCollisionBehavior empty, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(empty, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }

    @Override
    public Velocity visitNext(FrictionCollisionBehavior friction, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(friction, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }

    @Override
    public Velocity visitNext(MovableCollisionBehavior movable, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(movable, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }

    @Override
    public Velocity visitNext(PhysicalCollisionBehavior physical, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(physical, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
}
