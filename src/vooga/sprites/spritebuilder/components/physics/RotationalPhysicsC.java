package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;

import vooga.physics.collisionBehavior.EmptyCollisionBehavior;
import vooga.physics.fieldBehavior.EmptyFieldBehavior;
import vooga.physics.forceBehavior.RotationalForceBehavior;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Physics component class for adding rotational behavior to an object.
 * If rotational behavior is desired, add this component to the sprite's
 * component list.
 * 
 * Handles rotation only. For general purpose force behavior, add BasicPhysicsC.
 * 
 * @author Anne Weng
 *
 */
public class RotationalPhysicsC extends GeneralPhysicsC {

    /**
     * Calls the constructor with default state 'on'.
     * @param mass
     * @param center
     */
    public RotationalPhysicsC(double mass, Point center){
        this(mass, center, true);
    }
    
    /**
     * Creates the rotational behavior in the constructor and sets the
     * other two behavior types to empty.
     * @param mass
     * @param center
     * @param isOn
     */
    public RotationalPhysicsC(double mass, Point center, boolean isOn){
        super(new RotationalForceBehavior(mass, center),
                new EmptyFieldBehavior(), new EmptyCollisionBehavior(), isOn);
    }
    
    /**
     * Updates the the rotational force behavior's point of rotation.
     * Currently just uses the center of the sprite as its rotation point.
     * Sprites will need to add a getRotationPoint method to use a different point.
     */
    @Override
    public void update(Sprite s, long elapsedTime) {
        super.update(s, elapsedTime);
        //Updates the current center for force behavior
        int x = (int) s.getCenterX();
        int y = (int) s.getCenterY();
        super.myForceBehavior.updateBehavior(new Point(x,y));
    }

}
