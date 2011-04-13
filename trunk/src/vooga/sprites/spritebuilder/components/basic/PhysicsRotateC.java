package vooga.sprites.spritebuilder.components.basic;

import java.awt.Point;
import vooga.physics.interfaces.INewtonianPhysics;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPhysicsRotate;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.ISpriteCollider;
import vooga.sprites.spritebuilder.components.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

public class PhysicsRotateC extends BasicComponent implements IPhysicsRotate, ISpriteUpdater, ISpriteCollider{

    
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
    public void setPhysicsOnOff(boolean isOn) {
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
    public void applyRotationalForce(INewtonianPhysics physicalObject, Force force, Point pointOfApplication, long elapsedTime) {
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
    protected Object[] getFields() {
        return new Object[]{myMass, isOn};
    }

    @Override
    protected void setFields(Object... fields) {
        myMass = (Double) fields[0];
        if (fields.length > 1)
            isOn = (Boolean) fields[1];
        else
            isOn = true;
        
    }

    @Override
    public Point getCenter() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Velocity getVelocity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setVelocity(Velocity newVelocity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void collisionOccurred(ISpriteCollider otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution) {
        /*TODO: We can't use the "apply rotational force" method, because we don't actually have a force,
         * because we don't know how much time elapsed during the collision, so we need to implement this with
         * the conservation of rotational momentum theorem.
         * TODO: Also, with the currently implemented as ISpriteColliders (extending IPhysics),
         * can we actually implement this, because they currently can't get the other object's rotational
         * velocity?
         */
        
    }
}
