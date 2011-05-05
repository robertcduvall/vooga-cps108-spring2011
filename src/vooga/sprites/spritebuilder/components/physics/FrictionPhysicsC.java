package vooga.sprites.spritebuilder.components.physics;

import vooga.physics.collisionBehavior.FrictionCollisionBehavior;
import vooga.physics.forceBehavior.FrictionForceBehavior;
import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Physics component class for adding frictional behavior to an object.
 * If frictional behavior is desired, add this component to the sprite's
 * component list.
 * 
 * Handles friction only. For general purpose force behavior, add BasicPhysicsC.
 * 
 * @author Nathan Klug
 *
 */
public class FrictionPhysicsC extends AbstractPhysicsC {
    
    /**
     * Calls the constructor with default state 'on'.
     * @param mass
     * @param coefficientOfFriction
     */
    public FrictionPhysicsC(double mass, double coefficientOfFriction){
        this(mass, coefficientOfFriction, true);
    }
    
    /**
     * Creates the frictional behavior in the constructor and sets the
     * other two behavior types to empty.
     * @param mass
     * @param coefficientOfFriction
     * @param isOn
     */
    public FrictionPhysicsC(double mass, double coefficientOfFriction, boolean isOn){
        super(new FrictionForceBehavior(coefficientOfFriction, mass), new FrictionCollisionBehavior(), isOn);
    }
    
    @Override
    public void applyForce(AbstractForceGenerator force, long lengthOfApplication) {
        Object[] collisionProperties = getCollisionBehavior().getFields();
        if ((Boolean) collisionProperties[0])
            getForceBehavior().updateBehavior(collisionProperties);
        super.applyForce(force, lengthOfApplication);
    }
    
    /**
     * Updates the the rotational force behavior's point of rotation.
     * Currently just uses the center of the sprite as its rotation point.
     * Sprites will need to add a getRotationPoint method to use a different point.
     */
    @Override
    public void update(Sprite s, long elapsedTime) {
        super.update(s, elapsedTime);
        
        getCollisionBehavior().updateBehavior();
    }
    
    

}
