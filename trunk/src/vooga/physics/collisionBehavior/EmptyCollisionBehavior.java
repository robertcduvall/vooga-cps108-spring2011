package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.AbstractBehavior;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class EmptyCollisionBehavior extends AbstractBehavior{

    public Velocity collisionToVelocityChange(EmptyCollisionBehavior collisionBehavior, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return new Velocity(0, new Angle());
    }
}
