package vooga.physics.forceBehavior;

import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class MasslessForceBehavior extends EmptyForceBehavior{

    /**
     * This should only take a MassProportionalForceGenerator. Fix after fixing multiple dispatch
     */
    @Override
    public Velocity forceToVelocityChange(AbstractForceGenerator forceGen, long time){
        Force force = forceGen.getForce(this);
        return new Velocity(force.getMagnitude() * time, force.getAngle());
    }
}
