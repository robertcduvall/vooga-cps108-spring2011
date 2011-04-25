package vooga.physics.forceBehavior;

import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;

/**
 * Defines the standard Newtonian behavior for responding to a force.
 * 
 * @author Nathan Klug
 * 
 */
public class FrictionForceBehavior extends EmptyForceBehavior {

    private Angle mySurfaceTangent;
    private double myCoefficientOfFriction;
    private double myMass;
    private boolean collisionOccurring;

    public FrictionForceBehavior(double coefficientOfFriction, double mass) {
        mySurfaceTangent = new Angle();
        collisionOccurring = false;
        myCoefficientOfFriction = coefficientOfFriction;
        myMass = mass;
    }

    @Override
    public Velocity forceToVelocityChange(Force force, long time) {
        // TODO: Check calculation
        if (collisionOccurring) {
            double normalMagnitude = force.getPerpComponent(mySurfaceTangent);
            if (normalMagnitude < 0) {
                // Normal magnitude is negative so surfaceTangent is in
                // direction of friction
                normalMagnitude = -normalMagnitude;
            }
            else {
                // Normal magnitude is positive so surfaceTangent is in
                // direction opposite friction
                mySurfaceTangent.setNegativeAngle();
            }
            Force friction = new Force(normalMagnitude * myCoefficientOfFriction, mySurfaceTangent);

            return new Velocity(friction.getMagnitude() * time / myMass, friction.getAngle());
        }
        return new Velocity(0,0);
    }

    public void updateBehavior(Object... collisionProperties) {
        collisionOccurring = (Boolean) collisionProperties[0];
        if (collisionOccurring)
            mySurfaceTangent = (Angle) collisionProperties[1];
    }
}
