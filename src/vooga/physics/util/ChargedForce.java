package vooga.physics.util;

import vooga.util.math.Angle;

/**
 * Force which is charged, e.g. magnetic force. Polarization should be between
 * -1 and 1 for Newtonian physics.
 * 
 * @author Anne
 * 
 */
public class ChargedForce extends Force {
    private double myPolarization;

    public ChargedForce(double magnitude, Angle angle, double polarization) {
        super(magnitude, angle);
        myPolarization = polarization;
    }

    public ChargedForce(double xComponent, double yComponent, double polarization) {
        super(xComponent, yComponent);
        myPolarization = polarization;
    }
    
    public ChargedForce(double parallelComponent, double perpComponent, Angle angle, double polarization){
        super(parallelComponent, perpComponent, angle);
        myPolarization = polarization;
    }

    public void setPolarization(double newPolarization) {
        myPolarization = newPolarization;
    }

    public double getPolarization() {
        return myPolarization;
    }
    
    public ChargedForce addVector(ChargedForce otherVector) {
        super.addVector(otherVector);
        return this;
    }
    
    public ChargedForce scalarMultiply(double scalar){
        super.scalarMultiply(scalar);
        return this;
    }
}
