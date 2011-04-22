package vooga.sprites.spritebuilder.components.physics;

import java.awt.Point;
import vooga.physics.interfaces.newtonian.INewtonianRotate;
import vooga.physics.util.Force;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

public class PhysicsRotateC extends PhysicsC implements INewtonianRotate{

    private Sprite mySprite;
    private double myMass;
    private boolean isOn;


    @Override
    public void update(Sprite s, long elapsedTime) {
        //TODO: Does sprite already have a rotational velocity field?
        
    }

    @Override
    public double getRotationalVelocity() {
        return 0;
        //TODO: Does sprite already have a rotational velocity field?
    }

    @Override
    public void setRotationalVelocity(double newVelocity) {
      //TODO: Does sprite already have a rotational velocity field?
        
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
        
    }
    /**
     * Applies a force which causes rotation.
     * <br><br>
     * Applies the following equation to determine the change in angular velocity.
     * <br><img src="http://vooga-cps108-spring2011.googlecode.com/svn/trunk/src/vooga/physics/util/angularvelocity.PNG">
     * @param physicalObject
     * @param force
     * @param pointOfApplication
     * @param elapsedTime
     */
    public void applyRotationalForce(INewtonianRotate physicalObject, Force force, Point pointOfApplication, long elapsedTime) {
        MathVector radius = new MathVector(physicalObject.getCenter(), pointOfApplication);
        Angle theta = radius.getVectorAngle(force);
        double deltaOmega = force.getMagnitude() * theta.sin() * elapsedTime /
                               physicalObject.getMass() / radius.getMagnitude();
        physicalObject.setRotationalVelocity(physicalObject.getRotationalVelocity() + deltaOmega);
    }

    @Override
    public double getMass() {
        return myMass;
    }

    @Override
    protected int compareTo(BasicComponent o) {
        // TODO: what to do with this?
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[]{mySprite, myMass, isOn};
    }

    @Override
    protected void setFieldValues(Object... fields) {
        mySprite = (Sprite) fields[0];
        myMass = (Double) fields[1];
        if (fields.length > 2)
            isOn = (Boolean) fields[2];
        else
            isOn = true;
        
    }

}
