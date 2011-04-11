package vooga.util.physics;

import java.awt.Point;

/**
 * Creates a vector field about a point.
 * 
 * @author Nathan Klug
 *
 */
public class VectorField {
    
    private Point mySource;
    private double myMagnitude;
    private static final double GRAVITATION_CONSTANT = 1;
    
    public VectorField(Point source, double magnitude){
        mySource = source;
        myMagnitude = magnitude;
    }
    
    public static double getGravitationMagnitude(double mass){
        return mass*GRAVITATION_CONSTANT;
    }
    
}
