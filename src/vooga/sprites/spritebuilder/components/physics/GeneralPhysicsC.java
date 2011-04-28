package vooga.sprites.spritebuilder.components.physics;

import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.fieldBehavior.EmptyFieldBehavior;
import vooga.physics.forceBehavior.EmptyForceBehavior;

/**
 * General component which non-empty physics will extend.
 * 
 * Overrides all of the unwanted constructors for empty behavior by making them private.
 * A little boring.
 * 
 * @author Anne Weng
 * @author Nathan Klug
 * 
 */
public abstract class GeneralPhysicsC extends EmptyPhysicsC{
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    @SuppressWarnings("unused")
    private GeneralPhysicsC() {
    }
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    @SuppressWarnings("unused")
    private GeneralPhysicsC(boolean isOn) {
    }
    
    /**
     * Constructor has been made private to mask the parent constructor.
     */
    @SuppressWarnings("unused")
    private GeneralPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior, EmptyCollisionBehavior collisionBehavior){
        this(forceBehavior, fieldBehavior, collisionBehavior, true);
    }
    
    /**
     * Constructor has been made protected to mask the parent constructor.
     */
    protected GeneralPhysicsC(EmptyForceBehavior forceBehavior, EmptyFieldBehavior fieldBehavior, EmptyCollisionBehavior collisionBehavior, boolean isOn){
        super(forceBehavior, fieldBehavior, collisionBehavior, isOn);
    }
}
