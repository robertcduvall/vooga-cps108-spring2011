package vooga.physics.fieldBehavior;

import vooga.physics.util.Force;
import vooga.physics.util.IPointField;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

public class NewtonianFieldBehavior extends EmptyFieldBehavior {

    private VectorField myVectorField;

    public NewtonianFieldBehavior(VectorField vectorField) {
        myVectorField = vectorField;
    }

    @Override
    public Velocity fieldToVelocityChange(VectorField field, long time) {
        MathVector radius = new MathVector(myVectorField.getCenter(), field.getCenter());
        double magnitude = field.getMagnitude() * field.getConstant() * myVectorField.getMagnitude()
                / radius.getMagnitude();
        return new Velocity(magnitude * time / myVectorField.getMagnitude(), radius.getAngle());
    }
}
