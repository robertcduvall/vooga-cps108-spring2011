package vooga.physics.forceGenerator;

import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceBehavior.FieldForceBehavior;
import vooga.physics.util.Force;
import vooga.physics.util.VectorField;
import vooga.util.math.MathVector;

public class FieldForceGenerator extends AbstractForceGenerator {

    private VectorField myVectorField;
    
    public FieldForceGenerator(VectorField field){
        myVectorField = field;
    }
    
    public Force getForce(FieldForceBehavior forceTarget){
        if (myVectorField.getClass().equals(forceTarget.getVectorField().getClass())){
            VectorField otherField = forceTarget.getVectorField();
            MathVector radius = new MathVector(myVectorField.getCenter(), otherField.getCenter());
            double magnitude = otherField.getMagnitude() * otherField.getConstant() * myVectorField.getMagnitude()
                    / radius.getMagnitude();
            return new Force(magnitude, radius.getAngle());
        }
        return new Force(0,0);
    }
}
