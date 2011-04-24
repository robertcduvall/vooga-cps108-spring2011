package vooga.physics.collisionBehavior;

import java.awt.Point;
import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class MovableCollisionBehavior extends EmptyCollisionBehavior{

    private Velocity myVelocity;

    public MovableCollisionBehavior(Velocity velocity) {
        myVelocity = velocity;
    }

    public Velocity getVelocity() {
        return myVelocity;
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
}
