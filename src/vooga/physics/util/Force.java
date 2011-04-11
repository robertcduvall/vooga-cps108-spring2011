package vooga.physics.util;

import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * MathVector class for Forces.
 * 
 * @author Anne
 *
 */
public class Force extends MathVector {

    public Force(double magnitude, Angle angle) {
        super(magnitude, angle);
    }
    
    public Force(double xComponent, double yComponent){
        super(xComponent, yComponent);
    }
    
    public Force(double parallelComponent, double perpComponent, Angle angle){
        super(parallelComponent, perpComponent, angle);
    }

    public Force addVector(Force otherVector) {
        super.addVector(otherVector);
        return this;
    }
    
    public Force scalarMultiply(double scalar){
        super.scalarMultiply(scalar);
        return this;
    }
}
