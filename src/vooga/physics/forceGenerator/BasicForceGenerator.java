package vooga.physics.forceGenerator;

import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceBehavior.PhysicalForceBehavior;
import vooga.physics.util.Force;

public class BasicForceGenerator extends AbstractForceGenerator {

    private Force myForce;
    
    public BasicForceGenerator(Force force){
        myForce = force;
    }
    
    public Force getForce(EmptyForceBehavior forceTarget){
        return myForce;
    }
}
