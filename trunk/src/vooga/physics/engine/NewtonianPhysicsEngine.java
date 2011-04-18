package vooga.physics.engine;

import vooga.physics.interfaces.IPointField;
import vooga.physics.util.Force;
import java.awt.Point;
import vooga.physics.interfaces.newtonian.INewtonianMovable;
import vooga.physics.interfaces.newtonian.INewtonianPhysical;
import vooga.physics.interfaces.newtonian.INewtonianFriction;
import vooga.physics.interfaces.newtonian.INewtonianRotate;
import vooga.physics.util.MassProportionalForce;
import vooga.physics.util.Velocity;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

public class NewtonianPhysicsEngine extends BasePhysicsEngine {

    private static NewtonianPhysicsEngine myInstance;

    private NewtonianPhysicsEngine() {
        super();
    }

    public void applyForce(INewtonianPhysical object, Force force, long elapsedTime) {
        if (!super.applyForce(object, force, elapsedTime))
            force.applyForce(object, elapsedTime);
    }

    public void applyForce(INewtonianMovable object, MassProportionalForce force, long elapsedTime) {
        if (!super.applyForce(object, force, elapsedTime)) {
            if (force.getClass() == MassProportionalForce.class)
                ((MassProportionalForce) force).applyForce(object, elapsedTime);
        }
    }

    /**
     * Applies an external field to an object.
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     */
    public void applyField(IPointField object, IPointField field, long elapsedTime) {
        if (!super.applyField(object, field, elapsedTime)) {
            MathVector radius = new MathVector(object.getCenter(), field.getCenter());
            double magnitude = field.getPointMagnitude() * IPointField.constant * object.getPointMagnitude()
                    / radius.getMagnitude();
            applyFieldForce(object, new Force(magnitude, radius.getAngle()), elapsedTime);
        }
    }

    private void applyFieldForce(IPointField physicalObject, Force force, long elapsedTime) {
        Velocity deltaVelocity = new Velocity(force.getMagnitude() * elapsedTime / physicalObject.getPointMagnitude(),
                force.getAngle());
        Velocity spriteVelocity = physicalObject.getVelocity();
        spriteVelocity.addVector(deltaVelocity);
        physicalObject.setVelocity(spriteVelocity);

    }

    /**
     * Elastic collision method. Coefficient of Restitution is set to 1.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     */
    public void elasticCollision(Object object1, Object object2, Angle angleOfImpact, Point pointOfImpact) {
        collision(object1, object2, angleOfImpact, pointOfImpact, 1);
    }

    /**
     * Inelastic collision method. Coefficient of Restitution is set to 0.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     */
    public void inelasticCollision(Object object1, Object object2, Angle angleOfImpact, Point pointOfImpact) {
        collision(object1, object2, angleOfImpact, pointOfImpact, 0);
    }

    /**
     * If no coefficient of restitution is given, default is elastic collision.
     * 
     * @param object1
     * @param object2
     * @param angleOfImpact
     * @param pointOfImpact
     */
    public void collision(Object object1, Object object2, Angle angleOfImpact, Point pointOfImpact) {
        elasticCollision(object1, object2, angleOfImpact, pointOfImpact);
    }

    /**
     * Calculates the collision based on the masses and velocities of the
     * objects colliding. <br>
     * <br>
     * Source: <a href=
     * "http://en.wikipedia.org/wiki/Coefficient_of_restitution#Speeds_after_impact"
     * >Wikipedia</a>
     * 
     * @param thisObject
     * @param otherObject
     * @param angleOfImpact
     * @param pointOfCollision
     * @param coefficientOfRestitution
     */
    public void applyCollision(INewtonianPhysical thisObject, INewtonianPhysical otherObject, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (!super.applyCollision(thisObject, otherObject, angleOfImpact, pointOfImpact, coefficientOfRestitution)) {
            double myParallel = thisObject.getVelocity().getParallelComponent(angleOfImpact);
            double myPerp = thisObject.getVelocity().getPerpComponent(angleOfImpact);
            double otherParallel = otherObject.getVelocity().getParallelComponent(angleOfImpact);
            double otherPerp = otherObject.getVelocity().getPerpComponent(angleOfImpact);

            double parallelNumerator = thisObject.getMass() * myParallel + otherObject.getMass() * otherParallel
                    + otherObject.getMass() * coefficientOfRestitution * (otherParallel - myParallel);
            double perpNumerator = thisObject.getMass() * myPerp + otherObject.getMass() * otherPerp
                    + otherObject.getMass() * coefficientOfRestitution * (otherPerp - myPerp);
            double denominator = thisObject.getMass() + otherObject.getMass();

            Velocity newVelocity = new Velocity(perpNumerator / denominator, parallelNumerator / denominator,
                    angleOfImpact);
            thisObject.setVelocity(newVelocity);
        }
    }

    /**
     * Calculates collision based on the physical properties of the current
     * sprite and the velocity of the other sprite. Less exact calculation.
     * 
     * @param thisObject
     * @param otherObject
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public void applyCollision(INewtonianPhysical thisObject, INewtonianMovable otherObject, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (!super.applyCollision(thisObject, otherObject, angleOfImpact, pointOfImpact, coefficientOfRestitution)) {
            double myParallel = thisObject.getVelocity().getParallelComponent(angleOfImpact);
            double myPerp = thisObject.getVelocity().getPerpComponent(angleOfImpact);
            double otherParallel = otherObject.getVelocity().getParallelComponent(angleOfImpact);
            double otherPerp = otherObject.getVelocity().getPerpComponent(angleOfImpact);

            // Uses the coefficient of restitution in a way that works properly
            // for the endpoints (0 and 1), but I'm not
            // sure about the intermediate behavior?
            Velocity newVelocity = new Velocity(-myParallel * coefficientOfRestitution + otherParallel, -myPerp
                    * coefficientOfRestitution + otherPerp, angleOfImpact);
            thisObject.setVelocity(newVelocity);
        }
    }

    /**
     * Applies a force which causes rotation. <br>
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
    public void applyRotationalForce(INewtonianRotate physicalObject, Force force, Point pointOfApplication, long elapsedTime) {
        if (isOn()) {
            MathVector radius = new MathVector(physicalObject.getCenter(), pointOfApplication);
            Angle theta = radius.getVectorAngle(force);
            double deltaOmega = force.getMagnitude() * theta.sin() * elapsedTime / physicalObject.getMass()
                    / radius.getMagnitude();
            physicalObject.setRotationalVelocity(physicalObject.getRotationalVelocity() + deltaOmega);
        }
    }

    /**
     * Applies friction to an object.
     * 
     * @param physicalObject
     * @param force
     * @param surfaceTangent
     * @param elapsedTime
     */
    public void applyFriction(Object thisObject, Object otherObject, Force force, Angle surfaceTangent, long elapsedTime) {
        if (isOn()) {
            if (INewtonianFriction.class.isAssignableFrom(thisObject.getClass())) {
                double mu = 0; // Coefficient of friction
                if (INewtonianFriction.class.isAssignableFrom(otherObject.getClass())) {
                    // Get max coefficient of friction, otherwise mu = 0 for
                    // frictionless
                    mu = Math.max(((INewtonianFriction) thisObject).getCoefficientOfFriction(),
                            ((INewtonianFriction) otherObject).getCoefficientOfFriction());
                }
                double normalMagnitude = force.getPerpComponent(surfaceTangent);
                if (normalMagnitude < 0) {
                    // Normal magnitude is negative so surfaceTangent is in
                    // direction of friction
                    normalMagnitude = -normalMagnitude;
                }
                else {
                    // Normal magnitude is positive so surfaceTangent is in
                    // direction opposite friction
                    surfaceTangent.setNegativeAngle();
                }
                Force friction = new Force(normalMagnitude * mu, surfaceTangent);
                if (INewtonianPhysical.class.isAssignableFrom(thisObject.getClass())) {
                    friction.applyForce((INewtonianPhysical) thisObject, elapsedTime);
                }
            }
        }
    }

    public static NewtonianPhysicsEngine getInstance() {
        if (myInstance == null)
            myInstance = new NewtonianPhysicsEngine();
        return myInstance;
    }

}
