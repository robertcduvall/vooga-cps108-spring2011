package vooga.sprites.spritebuilder.components.physics;

import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.collisionBehavior.MovableCollisionBehavior;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceBehavior.MasslessForceBehavior;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

/**
 * A physics component that just has velocity. No mass.
 * 
 * @author Anne Weng
 * 
 */
public class MasslessPhysicsC extends AbstractPhysicsC{
    

    /**
     * Creates a VelocityPhysicsC which gets it's velocity from the sprite
     * @param velocity
     */
    public MasslessPhysicsC() {
        this(new Velocity(0,0),true);
    }
    
    /**
     * Creates a VelocityPhysicsC which has a given initial velocity (replaced by the sprite's velocity after update)
     * @param velocity
     */
    public MasslessPhysicsC(Velocity velocity) {
        this(velocity, true);
    }
    
    /**
     * Creates a VelocityPhysicsC with given velocity, mass, and state.
     * @param velocity
     * @param mass
     * @param isOn
     */
    public MasslessPhysicsC(Velocity velocity, boolean isOn) {
        super(new MasslessForceBehavior(), new EmptyCollisionBehavior(), isOn);
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: do we use this to compare whether a component is more specific.
        // for physics than another
        return 0;
    }

    @Override
    public void update(Sprite s, long elapsedTime) {
        super.update(s, elapsedTime);
        //Updates the current velocity for the collision behavior
        getCollisionBehavior().updateBehavior(getSpriteVelocityForPhysics(s));
    }
}
