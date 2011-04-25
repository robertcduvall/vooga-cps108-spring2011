package vooga.physics.fieldBehavior;

import vooga.physics.AbstractBehavior;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class EmptyFieldBehavior extends AbstractBehavior{
    
    public Velocity fieldToVelocityChange(VectorField field, long time){
        return new Velocity(0,new Angle());
    }
}
