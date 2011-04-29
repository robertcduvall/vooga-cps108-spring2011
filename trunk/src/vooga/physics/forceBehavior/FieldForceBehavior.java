package vooga.physics.forceBehavior;

import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.physics.util.Force;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import vooga.util.math.MathVector;

/**
 * Defines field behavior which is standard for Newtonian physics
 * 
 * @author Nathan Klug
 *
 */
public class FieldForceBehavior extends EmptyForceBehavior {

    private VectorField myVectorField;

    public FieldForceBehavior(VectorField vectorField) {
        myVectorField = vectorField;
    }
    
    public VectorField getVectorField(){
        return myVectorField;
    }

    @Override
    public Velocity forceToVelocityChange(AbstractForceGenerator appliedForce, long time) {
        Force force = appliedForce.getForce(this);
        
        return new Velocity(force.getMagnitude() * time / myVectorField.getMagnitude(), force.getAngle());
    }
}
