package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Physics behavior type for friction. Friction is a response to a collision so it
 * extends the EmptyCollisionBehavior.
 * 
 * @author Anne Weng
 *
 */
public class FrictionCollisionBehavior extends EmptyCollisionBehavior implements CollisionVisitor {

    private Angle collisionAngle;
    private boolean collisionOccurring;

    /**
     * Applies friction to an object. Returns the change in velocity as a result.
     * 
     * @param physicalObject
     * @param force
     * @param surfaceTangent
     * @param elapsedTime
     */
    public Velocity collisionToVelocityChange(EmptyCollisionBehavior otherCollisionBehavior, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        collisionAngle = angleOfImpact;
        collisionOccurring = true;
        return new Velocity(0,0);
    }

    public void updateBehavior(){
        collisionOccurring = false;
    }
    
    public Object[] getFields(){
        return new Object[]{collisionOccurring, collisionAngle};
    }
    
    @Override
    public Velocity visitFirst(CollisionVisitor first, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return first.visitNext(this, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
    
    @Override
    public Velocity visitNext(FrictionCollisionBehavior friction, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(friction, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
}
