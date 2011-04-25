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
public class PhysicalCollisionBehavior extends MovableCollisionBehavior{

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

        Velocity newVelocity = new Velocity(perpNumerator / denominator, parallelNumerator / denominator, angleOfImpact);
        
        return newVelocity.subtractVector(this.getVelocity());
    }
}
