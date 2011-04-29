package vooga.physics.forceGenerator;

import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.util.Force;

public abstract class AbstractForceGenerator {

    public Force getForce(EmptyForceBehavior forceTarget){
        return new Force(0,0);
    }
}
