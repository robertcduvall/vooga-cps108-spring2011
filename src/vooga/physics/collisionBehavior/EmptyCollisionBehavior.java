package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class EmptyCollisionBehavior {

    public Velocity collisionToVelocityChange(EmptyCollisionBehavior collisionBehavior, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return new Velocity(0, new Angle());
    }
    
}
