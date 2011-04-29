package vooga.physics.forceBehavior;

import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;

/**
 * Defines the standard Newtonian behavior for responding to a force.
 * 
 * @author Nathan Klug
 *
 */
public class PhysicalForceBehavior extends EmptyForceBehavior {

    protected double myMass;
    
    public PhysicalForceBehavior(double mass){
        myMass = mass;
    }
    
    public double getMass(){
        return myMass;
    }
    
    @Override
    public Velocity forceToVelocityChange(AbstractForceGenerator forceGen, long time) {
        Force force = forceGen.getForce(this);
        return new Velocity(force.getMagnitude() * time / myMass, force.getAngle());
    }

}
