package vooga.sprites.spritebuilder.components.basic;

import java.awt.Point;
import vooga.physics.calculators.PhysicsCalculator;
import vooga.physics.engine.PhysicsEngine;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPointField;
import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.ISpriteCollider;
import vooga.sprites.spritebuilder.components.ISpriteUpdater;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * Physics Component of a Sprite, extends BasicComponent and implements IPhysics.
 * 
 * @author Nathan Klug
 *
 */
public class PhysicsC extends BasicComponent implements IPhysics, ISpriteUpdater, ISpriteCollider
{
    private Sprite mySprite;
    private double myMass;
    private boolean isOn;
    
    @Override
    protected int compareTo (BasicComponent o)
    {
        //TODO: do we use this to compare whether a component is more specific for physics than another
        return 0;
    }


    @Override
    protected Object[] getFields ()
    {
        return this.getClass().getFields();
    }


    @Override
    protected void setFields (Object ... fields)
    {
        mySprite = (Sprite) fields[0];
        myMass = (Double) fields[1];
        if (fields.length > 2)
            isOn = (Boolean) fields[2];
        else
            isOn = true;
    }


    @Override
    public double getMass ()
    {
        return myMass;
    }


    @Override
    public Point getCenter ()
    {
        return new Point((int) mySprite.getCenterX(), (int) mySprite.getCenterY());
    }


    @Override
    public Velocity getVelocity ()
    {
        return new Velocity(mySprite.getHorizontalSpeed(), -mySprite.getVerticalSpeed());
    }


    @Override
    public void setVelocity (Velocity newVelocity)
    {
        mySprite.setHorizontalSpeed(newVelocity.getXComponent());
        mySprite.setVerticalSpeed(-newVelocity.getYComponent());
        
    }


    @Override
    public boolean isOn ()
    {
        return isOn;
    }


    @Override
    public void setPhysicsOnOff (boolean isOn)
    {
        this.isOn = isOn;
    }


    @Override
    public void update(Sprite s, long elapsedTime) {
        if (isOn())
            PhysicsEngine.getInstance().applyWorldForces(this, elapsedTime);
    }

    /**
     * Calculates the collision based on the masses and velocities of the objects colliding.
     * <br><br>Source: <a href="http://en.wikipedia.org/wiki/Coefficient_of_restitution#Speeds_after_impact">Wikipedia</a>
     * @param thisObject
     * @param otherObject
     * @param angleOfImpact
     * @param pointOfCollision
     * @param coefficientOfRestitution
     */
    public void collisionOccurred(PhysicsC otherObject, Angle angleOfImpact, Point pointOfCollision, double coefficientOfRestitution) {
        if (isOn()) {
            PhysicsEngine.getInstance().basicCollisionOccurred(this, otherObject, angleOfImpact, coefficientOfRestitution);
        }
    }

}
