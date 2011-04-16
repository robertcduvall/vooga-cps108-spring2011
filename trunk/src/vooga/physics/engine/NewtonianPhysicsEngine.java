package vooga.physics.engine;

import java.util.Collection;
import vooga.physics.interfaces.IPointField;
import vooga.physics.interfaces.IPhysicsToggle;
import vooga.physics.util.Force;
import java.awt.Point;
import java.util.HashSet;
import vooga.physics.calculators.PhysicsCalculator;
import vooga.physics.interfaces.IMovable;
import vooga.physics.interfaces.INewtonianPhysics;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPhysicsCollider;
import vooga.physics.interfaces.IPhysicsFriction;
import vooga.physics.interfaces.IPhysicsRotate;
import vooga.physics.util.MassProportionalForce;
import vooga.physics.util.Velocity;
import vooga.reflection.Reflection;
import vooga.util.buildable.components.IComponent;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

public class NewtonianPhysicsEngine extends AbstractPhysicsEngine implements IPhysicsToggle{

    private Collection<Force> worldForces;
    private Collection<IPointField> pointForces;
    private boolean isOn;
    private static NewtonianPhysicsEngine myInstance;
    
    private NewtonianPhysicsEngine(){
        worldForces = new HashSet<Force>();
        pointForces = new HashSet<IPointField>();
        isOn = true;
    }

    /**
     * Adds a force to the collection of worldwide forces.
     * 
     * @param force
     */
    public void addGlobalForce(Force force) {
        worldForces.add(force);
    }

    /**
     * Removes a force from the collection of worldwide forces.
     * 
     * @param force
     */
    public void removeGlobalForce(Force force) {
        worldForces.remove(force);
    }

    /**
     * Adds a point field to the collection of worldwide fields
     * 
     * @param field
     */
    public void addGlobalPointField(IPointField field){
        pointForces.add(field);
    }

    /**
     * Removes a point field from the collection of worldwide point fields.
     * 
     * @param field
     */
    public void removeGlobalForce(IPointField field) {
        pointForces.remove(field);
    }

    /**
     * Applies all of the worldwide forces to a sprite. This only applies the
     * MassProportionalForces, such as gravity, which do not need physical
     * properties like mass. Do not use this method if your sprite has a
     * physical nature, or else these forces will be applied twice
     * 
     * TODO: should we change it so applying the world forces to a physical
     * object does not apply the mass proportional forces?
     * 
     * @param sprite
     * @param elapsedTime
     */
    public void applyWorldForces(IMovable movableObject, long elapsedTime) {
        if (isOn) {
            for (Force f : worldForces) {
                applyForce(movableObject, f, elapsedTime);
            }
        }
    }
    
    public void applyForce(IPhysics object, Force f, long elapsedTime){
        f.applyForce(object, elapsedTime);
    }
    
    public void applyForce(IMovable object, Force f, long elapsedTime){
        if (f.getClass() == MassProportionalForce.class)
            ((MassProportionalForce)f).applyForce(object, elapsedTime);
    }

    /**
     * Applies all of the worldwide point fields to something which has that same field
     * @param affectedObject
     * @param elapsedTime
     */
    public void applyPointForces(IPointField affectedObject, long elapsedTime) {
        for (IPointField field : pointForces) {
            if (field.getClass() == affectedObject.getClass()) {
                applyField(affectedObject, field, elapsedTime);
            }
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
        if (isOn()) {
            MathVector radius = new MathVector(object.getCenter(), field.getCenter());
            double magnitude = field.getPointMagnitude() * IPointField.constant * object.getPointMagnitude()
            / radius.getMagnitude();
            applyFieldForce(object, new Force(magnitude, radius.getAngle()), elapsedTime);
        }
    }

    private void applyFieldForce(IPointField physicalObject, Force force, long elapsedTime) {
        if (isOn()) {
            Velocity deltaVelocity = new Velocity(force.getMagnitude() * elapsedTime
                    / physicalObject.getPointMagnitude(), force.getAngle());
            Velocity spriteVelocity = physicalObject.getVelocity();
            spriteVelocity.addVector(deltaVelocity);
            physicalObject.setVelocity(spriteVelocity);
        }
    
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
    public void basicCollisionOccurred(IPhysics thisObject, IPhysics otherObject, Angle angleOfImpact, double coefficientOfRestitution) {
        if (isOn()) {
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
    public void basicCollisionOccurred(IPhysics thisObject, IMovable otherObject, Angle angleOfImpact, double coefficientOfRestitution) {
        if (isOn()) {
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
    public void applyRotationalForce(IPhysicsRotate physicalObject, Force force, Point pointOfApplication, long elapsedTime) {
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
    public void applyFriction(IPhysicsFriction objectWithFriction, IPhysics otherObject, Force force, Angle surfaceTangent, long elapsedTime) {
        if (isOn()) {
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
            Force friction = new Force(normalMagnitude * objectWithFriction.getCoefficientOfFriction(), surfaceTangent);
            friction.applyForce(otherObject, elapsedTime);
        }
    }

    public static NewtonianPhysicsEngine getInstance() {
        if (myInstance == null)
            myInstance = new NewtonianPhysicsEngine();
        return myInstance;
    }

}
