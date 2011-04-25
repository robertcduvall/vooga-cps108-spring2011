package vooga.physics.fieldBehavior;

import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.util.math.MathVector;

public class NewtonianFieldBehavior extends EmptyFieldBehavior {

    private VectorField myVectorField;

    public NewtonianFieldBehavior(VectorField vectorField) {
        myVectorField = vectorField;
    }

    @Override
    public Velocity fieldToVelocityChange(VectorField otherField, long time) {
        MathVector radius = new MathVector(myVectorField.getCenter(), otherField.getCenter());
        double magnitude = otherField.getMagnitude() * otherField.getConstant() * myVectorField.getMagnitude()
                / radius.getMagnitude();
        return new Velocity(magnitude * time / myVectorField.getMagnitude(), radius.getAngle());
    }
}
