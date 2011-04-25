package vooga.physics.forceBehavior;

import vooga.physics.util.Force;
import vooga.physics.util.Velocity;

public class NewtonianForceBehavior extends EmptyForceBehavior {

    protected double myMass;
    
    public NewtonianForceBehavior(double mass){
        myMass = mass;
    }
    
    @Override
    public Velocity forceToVelocityChange(Force force, long time) {
        return new Velocity(force.getMagnitude() * time / myMass, force.getAngle());
    }

}
