package vooga.physics.tests;

import java.awt.Point;

import vooga.physics.newtonianProperties.INewtonianPhysical;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Test object class which implements the necessary interfaces for testing.
 * 
 * @author Anne Weng
 *
 */
public class TestingNewtonianObject implements INewtonianPhysical {
    private boolean isOn;
    private Point center;
    private Velocity velocity;
    private double mass;
    
    public TestingNewtonianObject(Point center, Velocity velocity, double mass) {
        isOn = true;
        this.center = center;
        this.velocity = velocity;
        this.mass = mass;
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
    
    public void setMass(double newMass){
        mass = newMass;
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
    public void setVelocity(Velocity newVelocity) {
        velocity = newVelocity;
    }

}
