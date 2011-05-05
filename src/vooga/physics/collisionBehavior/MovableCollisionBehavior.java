package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Collision behavior for objects which have a velocity.
 * 
 * @author Nathan Klug
 *
 */
public class MovableCollisionBehavior extends EmptyCollisionBehavior implements CollisionVisitor {

    private Velocity myVelocity;

    public MovableCollisionBehavior(Velocity velocity) {
        myVelocity = velocity;
    }

    public Velocity getVelocity() {
        return myVelocity;
    }
    
    /**
     * Needed to keep the behavior synced with the object
     * @param newVelocity
     */
    public void updateVelocity(Velocity newVelocity){
        myVelocity = newVelocity;
    }


    public Velocity collisionToVelocityChange(MovableCollisionBehavior otherObject, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        double myParallel = this.getVelocity().getParallelComponent(angleOfImpact);
        double myPerp = this.getVelocity().getPerpComponent(angleOfImpact);
        double otherParallel = otherObject.getVelocity().getParallelComponent(angleOfImpact);
        double otherPerp = otherObject.getVelocity().getPerpComponent(angleOfImpact);

        // Uses the coefficient of restitution in a way that works properly
        // for the endpoints (0 and 1), but I'm not
        // sure about the intermediate behavior?
        Velocity newVelocity = new Velocity(-myParallel * coefficientOfRestitution + otherParallel, -myPerp
                * coefficientOfRestitution + otherPerp, angleOfImpact);
        
        return newVelocity.subtractVector(this.getVelocity());
    }
    
    @Override
    public void updateBehavior(Object... newValues){
        if (newValues.length != 0)
            myVelocity = (Velocity) newValues[0];
    }
    
    @Override
    public Velocity visitFirst(CollisionVisitor first, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return first.visitNext(this, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
    
    @Override
    public Velocity visitNext(MovableCollisionBehavior movable, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(movable, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
}
