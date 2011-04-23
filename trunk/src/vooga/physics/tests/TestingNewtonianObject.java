package vooga.physics.tests;

import java.awt.Point;

import vooga.physics.newtonianInterfaces.INewtonianPhysical;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

public class TestingNewtonianObject implements INewtonianPhysical {
    private boolean isOn;
    private Point center;
    private Velocity velocity;
    private double mass;
    
    public TestingNewtonianObject() {
        isOn = false;
        center = new Point(0,0);
        velocity = new Velocity(0, new Angle(0));
        mass = 100;
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
