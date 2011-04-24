package vooga.physics.fieldBehavior;

import vooga.physics.util.Force;
import vooga.physics.util.IPointField;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class EmptyFieldBehavior {
    
    public Velocity fieldToVelocityChange(VectorField field, long time){
        return new Velocity(0,new Angle());
    }
}
