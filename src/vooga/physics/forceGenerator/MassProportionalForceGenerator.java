package vooga.physics.forceGenerator;

import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceBehavior.MasslessForceBehavior;
import vooga.physics.forceBehavior.PhysicalForceBehavior;
import vooga.physics.util.Force;

public class MassProportionalForceGenerator extends AbstractForceGenerator {
    
    private Force myForce;
    
    public MassProportionalForceGenerator(Force force){
        myForce = force;
    }
    
    /**
     * This method shouldn't exist. Temporary fix until multiple dispatch works
     */
    public Force getForce(EmptyForceBehavior forceTarget){
        return myForce;
    }
    
    public Force getForce(MasslessForceBehavior forceTarget){
        return myForce;
    }
    
    public Force getForce(PhysicalForceBehavior forceTarget){
        return new Force(myForce.getMagnitude()*forceTarget.getMass(), myForce.getAngle());
    }
}
