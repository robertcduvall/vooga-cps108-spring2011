package vooga.physics.calculators;

import java.awt.Point;

import vooga.physics.interfaces.INewtonianPhysics;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.util.Force;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * Extends PhysicsCalculator by updating for things like friction, rotation
 * 
 * @author Nathan Klug
 * @author Anne Weng
 * 
 */
public class NewtonianCalculator extends PhysicsCalculator {

    @Override
    public void updateWithPhysics(long elapsedTime, IPhysics physicalObject) {

        if (physicalObject.isOn()) {

            this.updateWithPhysics(elapsedTime, physicalObject);
            if (physicalObject instanceof INewtonianPhysics) {
                // TODO: do whatever should be done here

            }

        }
    }

    /**
     * Applies a force which causes rotation.
     * deltaOmega = F * sin(theta) * deltaT / (m * r)
     * <br><img src="src/vooga/physics/util/angularvelocity.PNG">
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

    /**
     * Applies friction to an object.
     * @param physicalObject
     * @param force
     * @param surfaceTangent
     * @param elapsedTime
     */
    public void applyFriction(INewtonianPhysics physicalObject, Force force, Angle surfaceTangent, long elapsedTime) {
        double normalMagnitude = force.getPerpComponent(surfaceTangent);
        if (normalMagnitude < 0) {
            //Normal magnitude is negative so surfaceTangent is in direction of friction
            normalMagnitude = -normalMagnitude;
        }
        else {
            //Normal magnitude is positive so surfaceTangent is in direction opposite friction
            surfaceTangent.setNegativeAngle();
        }
        Force friction = new Force(normalMagnitude * physicalObject.getCoefficientOfFriction(), surfaceTangent);
        applyForce(physicalObject, friction, elapsedTime);
    }

}
