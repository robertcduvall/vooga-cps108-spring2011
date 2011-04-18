package vooga.physics.interfaces;

import vooga.physics.util.Force;

public interface IPhysicsCustomForce {

    public void applyForce(Force f, long timeElapsed);
}
