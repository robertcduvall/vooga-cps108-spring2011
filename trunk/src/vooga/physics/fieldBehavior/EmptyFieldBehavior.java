package vooga.physics.fieldBehavior;

import vooga.physics.AbstractBehavior;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Exactly what the class name says. Defines field behavior which does nothing. To be used in Vooga
 * in the case where a physics component does not define field behavior.
 * 
 * @author Nathan Klug
 *
 */
public class EmptyFieldBehavior extends AbstractBehavior{
    
    /**
     * Returns the change in an object's velocity as a result of a field acting upon it.
     * @param field
     * @param time the time the field is acting
     * @return the change in velocity
     */
    public Velocity fieldToVelocityChange(VectorField field, long time){
        return new Velocity(0,new Angle());
    }
}
