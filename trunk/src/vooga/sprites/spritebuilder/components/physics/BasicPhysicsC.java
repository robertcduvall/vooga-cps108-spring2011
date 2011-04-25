package vooga.sprites.spritebuilder.components.physics;

import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.collisionBehavior.PhysicalCollisionBehavior;
import vooga.physics.fieldBehavior.EmptyFieldBehavior;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceBehavior.NewtonianForceBehavior;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

/**
 * Basic Physics Component of a Sprite. Force behavior is Newtonian, Field behavior is empty,
 * and Collision behavior is Physical.
 * 
 * @author Nathan Klug
 * 
 */
public class BasicPhysicsC extends EmptyPhysicsC{
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    @SuppressWarnings("unused")
    private BasicPhysicsC() {
    }
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    @SuppressWarnings("unused")
    private BasicPhysicsC(boolean isOn) {
    }

    /**
     * Creates a BasicPhysicsC with given velocity and mass and the default 'on' state.
     * @param velocity
     * @param mass
     */
    public BasicPhysicsC(Velocity velocity, double mass) {
        this(velocity, mass, true);
    }
    
    /**
     * Creates a BasicPhysicsC with given velocity, mass, and state.
     * @param velocity
     * @param mass
     * @param isOn
     */
    public BasicPhysicsC(Velocity velocity, double mass, boolean isOn) {
        this(new NewtonianForceBehavior(mass),new EmptyFieldBehavior(), new PhysicalCollisionBehavior(velocity, mass), isOn);
    }
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    @SuppressWarnings("unused")
    private BasicPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior, EmptyCollisionBehavior collisionBehavior){
        this(forceBehavior, fieldBehavior, collisionBehavior, true);
    }
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    private BasicPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior, EmptyCollisionBehavior collisionBehavior, boolean isOn){
        super(forceBehavior, fieldBehavior, collisionBehavior, isOn);
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
        super.myCollisionBehavior.updateBehavior(getSpriteVelocityForPhysics(s));
    }
}
