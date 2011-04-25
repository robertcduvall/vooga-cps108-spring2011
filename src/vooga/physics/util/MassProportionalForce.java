package vooga.physics.util;

import vooga.util.math.Angle;

/**
 * Implementation of a force which is proportional to mass, such as gravity.
 * 
 * @author Nathan Klug
 *
 */
public class MassProportionalForce extends Force {

    public MassProportionalForce(double magnitude, Angle angle) {
        super(magnitude, angle);
    }
    
    @Override
    public MassProportionalForce addVector(Force otherVector) {
        super.addVector(otherVector);
        return this;
    }
    
    @Override
    public MassProportionalForce scalarMultiply(double scalar) {
        super.scalarMultiply(scalar);
        return this;
    }

}
