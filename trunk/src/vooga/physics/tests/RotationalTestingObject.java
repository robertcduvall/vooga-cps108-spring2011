package vooga.physics.tests;

import java.awt.Point;

import vooga.physics.newtonianProperties.INewtonianRotate;

/**
 * Test object class created for testing rotational physics.
 * @author Anne Weng
 *
 */

public class RotationalTestingObject implements INewtonianRotate {

    private boolean isOn;
    private double rotationalVelocity;
    private double mass;
    private Point center;
    
    public RotationalTestingObject(Point center, double velocity, double mass){
        isOn = true;
        this.mass = mass;
        this.center = center;
        rotationalVelocity = velocity;
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
    public double getMass() {
        return mass;
    }

    @Override
    public double getRotationalVelocity() {
        return rotationalVelocity;
    }

    @Override
    public void setRotationalVelocity(double newVelocity) {
        rotationalVelocity = newVelocity;
    }

    @Override
    public Point getCenter() {
        return center;
    }

}
