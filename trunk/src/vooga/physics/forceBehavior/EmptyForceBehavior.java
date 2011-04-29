package vooga.physics.forceBehavior;

import vooga.physics.AbstractBehavior;
import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;
/**
 * Exactly what the class name says. Defines force behavior which does nothing. To be used in Vooga
 * in the case where a physics component does not define force behavior.
 * @author Nathan Klug
 *
 */
public class EmptyForceBehavior extends AbstractBehavior{

    /**
     * Returns the change in an object's velocity as a result of a force acting upon it.
     * @param force
     * @param time the time the force is acting
     * @return the change in velocity
     */
    public Velocity forceToVelocityChange(AbstractForceGenerator force, long time){
        return new Velocity(0,new Angle());
    }
}
