package vooga.physics.tests;

import java.awt.Point;

import vooga.physics.util.IPointField;
import vooga.physics.util.Velocity;

/**
 * Test object class created for testing calculations involving point fields.
 * @author Anne Weng
 *
 */

public class PointFieldTestingObject implements IPointField {

    private boolean isOn;
    private Velocity velocity;
    private double magnitude;
    private Point center;
    private double constant;
    
    public PointFieldTestingObject(double constant, Point center, Velocity velocity, double magnitude){
        isOn = true;
        this.constant = constant;
        this.center = center;
        this.velocity = velocity;
        this.magnitude = magnitude;
    }
    
    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
        
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public double getPointMagnitude() {
        return magnitude;
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public Velocity getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Velocity spriteVelocity) {
        velocity = spriteVelocity;
    }

    @Override
    public double getConstant() {
        return constant;
    }

}
