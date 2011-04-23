package vooga.physics.customBehavior;

import vooga.physics.util.Force;
/**
 * Interface which should be implemented if one would like to override the default processing
 * of the application of a force.
 * @author Nathan Klug
 *
 */
public interface IPhysicsCustomForce {

    public void applyForce(Force f, long timeElapsed);
}
