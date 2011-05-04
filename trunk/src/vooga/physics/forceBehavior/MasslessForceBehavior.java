package vooga.physics.forceBehavior;

import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class MasslessForceBehavior extends EmptyForceBehavior{

    
    public Velocity forceToVelocityChange(MassProportionalForceGenerator forceGen, long time){
        Force force = forceGen.getForce(this);
        return new Velocity(force.getMagnitude() * time, force.getAngle());
    }
}
