package vooga.physics.util;

import java.awt.Point;

public abstract class VectorField {

    private double CONSTANT = 0;
    
    private double myMagnitude;
    private Point myCenter;
    
    public VectorField(double magnitude, Point center){
        myMagnitude = magnitude;
        myCenter = center;
    }
    
    public double getMagnitude(){
        return myMagnitude;
    }
    
    public double getConstant(){
        return CONSTANT;
    }
    
    public Point getCenter(){
        return myCenter;
    }
}
