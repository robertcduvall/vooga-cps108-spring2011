package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Defines collision behavior for an object which has mass and velocity.
 * 
 * @author Nathan Klug
 *
 */
public class PhysicalCollisionBehavior extends MovableCollisionBehavior implements CollisionVisitor {

    private double myMass;

    public PhysicalCollisionBehavior(Velocity velocity, double mass) {
        super(velocity);
        myMass = mass;
    }

    public double getMass() {
        return myMass;
    }

    public Velocity collisionToVelocityChange(PhysicalCollisionBehavior otherObject, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        double myParallel = this.getVelocity().getParallelComponent(angleOfImpact);
        double myPerp = this.getVelocity().getPerpComponent(angleOfImpact);
        double otherParallel = otherObject.getVelocity().getParallelComponent(angleOfImpact);
        double otherPerp = otherObject.getVelocity().getPerpComponent(angleOfImpact);

        double parallelNumerator = this.getMass() * myParallel + otherObject.getMass() * otherParallel
        + otherObject.getMass() * coefficientOfRestitution * (otherParallel - myParallel);
        double perpNumerator = this.getMass() * myPerp + otherObject.getMass() * otherPerp + otherObject.getMass()
        * coefficientOfRestitution * (otherPerp - myPerp);
        double denominator = this.getMass() + otherObject.getMass();

        Velocity newVelocity = new Velocity(parallelNumerator / denominator, perpNumerator / denominator, angleOfImpact);

        return newVelocity.subtractVector(this.getVelocity());
    }
    
    @Override
    public Velocity visitFirst(CollisionVisitor first, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return first.visitNext(this, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
    
    
    @Override
    public Velocity visitNext(PhysicalCollisionBehavior physical, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        return collisionToVelocityChange(physical, angleOfImpact, pointOfImpact, coefficientOfRestitution);
    }
}
