package vooga.physics.forceBehavior;

import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class EmptyForceBehavior {

    public Velocity forceToVelocityChange(Force force, long time){
        return new Velocity(0,new Angle());
    }
}
