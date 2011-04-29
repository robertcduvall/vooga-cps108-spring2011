package vooga.sprites.spritebuilder.components.physics;

import vooga.physics.collisionBehavior.PhysicalCollisionBehavior;
import vooga.physics.forceBehavior.FieldForceBehavior;
import vooga.physics.forceBehavior.PhysicalForceBehavior;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;

/**
 * Newtonian Physics Component of a Sprite. Force behavior is Newtonian, 
 * Field behavior is Newtonian, and Collision behavior is Physical.
 * 
 * @author Anne Weng
 * @author Nathan Klug
 * 
 */
public class NewtonianPhysicsC extends AbstractPhysicsC{

    /**
     * Creates a NewtonianPhysicsC with given velocity and mass and the default 'on' state.
     * @param velocity
     * @param mass
     */
    public NewtonianPhysicsC(Velocity velocity, double mass, VectorField field) {
        this(velocity, mass, field, true);
    }
    
    /**
     * Creates a NewtonianPhysicsC with given velocity, mass, and state.
     * @param velocity
     * @param mass
     * @param isOn
     */
    public NewtonianPhysicsC(Velocity velocity, double mass, VectorField field, boolean isOn) {
        super(new PhysicalForceBehavior(mass), new PhysicalCollisionBehavior(velocity, mass), isOn);
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
