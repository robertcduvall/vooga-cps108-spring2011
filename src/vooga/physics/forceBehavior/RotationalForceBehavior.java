package vooga.physics.forceBehavior;

import java.awt.Point;

import vooga.physics.util.Force;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * Adds rotational functionality to an object.
 * 
 * @author Anne Weng
 *
 */
public class RotationalForceBehavior extends EmptyForceBehavior {

    private double myMass;
    private Point myCenter;

    public RotationalForceBehavior(double mass, Point center) {
        myMass = mass;
        myCenter = center;
    }

    /**
     * Applies a force which causes rotation. Returns the change in angular velocity.<br>
     * <br>
     * Applies the following equation to determine the change in angular
     * velocity. <br>
     * <img src=
     * "http://vooga-cps108-spring2011.googlecode.com/svn/trunk/src/vooga/physics/util/angularvelocity.PNG"
     * >
     * 
     * @param physicalObject
     * @param force
     * @param pointOfApplication
     * @param elapsedTime
     */
    public Velocity applyRotationalForce(Force force, Point pointOfApplication, long elapsedTime) {
        MathVector radius = new MathVector(myCenter, pointOfApplication);
        Angle theta = radius.getVectorAngle(force);
        double deltaOmega = force.getMagnitude() * -theta.sin() * elapsedTime / myMass
        / radius.getMagnitude();
        return new Velocity(deltaOmega, new Angle(0));
    }


    @Override
    public void updateBehavior(Object... newValues){
        if (newValues.length > 0)
            myCenter = (Point) newValues[0];
    }

}
