package vooga.physics.engine;

import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;
import vooga.physics.calculators.PhysicsCalculator;
import vooga.physics.interfaces.INewtonianPhysics;
import vooga.physics.interfaces.IPhysics;
import vooga.physics.interfaces.IPointField;
import vooga.physics.util.Force;
import vooga.physics.util.MassProportionalForce;
import vooga.physics.util.Velocity;
import vooga.reflection.Reflection;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.ISpritePhysicsCollider;
import vooga.sprites.spritebuilder.components.basic.PhysicsC;
import vooga.util.buildable.components.IComponent;
import vooga.util.math.Angle;
import vooga.util.math.MathVector;

/**
 * The big, giant physics engine class that has all methods in physics you
 * should ever need to use
 * 
 * @author Nathan Klug
 * @author Anne Weng
 * 
 */
public class PhysicsEngine {

    private static Collection<Force> worldForces;
    private static Collection<IPointField> pointForces;
    private static PhysicsEngine myInstance;
    private static boolean isOn;

    private PhysicsEngine() {
        worldForces = new HashSet<Force>();
        pointForces = new HashSet<IPointField>();
        isOn = true;
    }

    /**
     * Returns an instance of WorldForceManager.
     * 
     * @return
     */
    public static PhysicsEngine getInstance() {
        if (myInstance == null) {
            myInstance = new PhysicsEngine();
        }
        return myInstance;
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
    public void applyWorldForces(Sprite sprite, long elapsedTime) {
        if (isOn) {
            for (Force f : worldForces) {
                if (f instanceof MassProportionalForce)
                    ((MassProportionalForce) f).applyForce(sprite, elapsedTime);
            }
        }
    }

    /**
     * Applies all of the worldwide forces to something with a physics nature.
     * 
     * @param physics
     * @param elapsedTime
     */
    public void applyWorldForces(IPhysics physicalObject, long elapsedTime) {
        if (isOn) {
            for (Force f : worldForces) {
                f.applyForce(physicalObject, elapsedTime);
            }
        }
    }

    public void applyPointForces(IPointField affectedObject, long elapsedTime) {
        for (IPointField field : pointForces) {
            if (field.getClass() == affectedObject.getClass()) {
                PhysicsCalculator.getInstance().applyField(affectedObject, field, elapsedTime);
            }
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
    public void elasticCollision(Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact) {
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
    public void inelasticCollision(Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact) {
        collision(object1, object2, angleOfImpact, pointOfImpact, 0);
    }

    /**
     * General collision method. Tells the two physical objects that a collision
     * occurred.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public void collision(Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (isOn) {
            for (IComponent c : object1.getComponents()) {
                if (c instanceof ISpritePhysicsCollider) {
                    ((ISpritePhysicsCollider) c).collisionOccurred(object2, angleOfImpact, pointOfImpact,
                            coefficientOfRestitution);
                }
            }
            for (IComponent c : object2.getComponents()) {
                if (c instanceof ISpritePhysicsCollider) {
                    ((ISpritePhysicsCollider) c).collisionOccurred(object1, angleOfImpact, pointOfImpact,
                            coefficientOfRestitution);
                }
            }
        }
    }

    /**
     * If no coefficient of restitution is given, default is elastic collision.
     * 
     * @param object1
     * @param object2
     * @param angleOfImpact
     * @param pointOfImpact
     */
    public void collision(Sprite object1, Sprite object2, Angle angleOfImpact, Point pointOfImpact) {
        elasticCollision(object1, object2, angleOfImpact, pointOfImpact);
    }

    /**
     * Returns a boolean value representing if physics is on
     * 
     * @return
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Turns physics off
     */
    public void turnOff() {
        isOn = false;
    }

    /**
     * Turns physics on
     */
    public void turnOn() {
        isOn = true;
    }

    /**
     * Sets the physics on/off based on the input boolean
     * 
     * @param newState
     */
    public void setPhysicsOnOff(boolean newState) {
        isOn = newState;
    }

    /**
     * Turns physics on if it is off and turns physics off if it is on
     */
    public void togglePhysicsOnOff() {
        isOn = !isOn;
    }

    /**
     * Updates based on the basic physics (like gravity) in the world.
     * 
     * @param elapsedTime
     * @param physicalObject
     *            the object to update
     */
    public void updateWithPhysics(long elapsedTime, IPhysics physicalObject) {
        if (isOn()) {
            applyWorldForces(physicalObject, elapsedTime);
        }
    }

    /**
     * Applies an external field to an IPhysics object.
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     */
    public void applyField(IPointField physicalObject, IPointField field, long elapsedTime) {
        if (isOn()) {
            MathVector radius = new MathVector(physicalObject.getCenter(), field.getCenter());
            double magnitude = field.getPointMagnitude() * field.CONSTANT * physicalObject.getPointMagnitude()
                    / radius.getMagnitude();
            applyForce(physicalObject, new Force(magnitude, radius.getAngle()), elapsedTime);
        }
    }

    private void applyForce(IPointField physicalObject, Force force, long elapsedTime) {
        if (isOn()) {
            Velocity deltaVelocity = new Velocity(force.getMagnitude() * elapsedTime
                    / physicalObject.getPointMagnitude(), force.getAngle());
            Velocity spriteVelocity = physicalObject.getVelocity();
            spriteVelocity.addVector(deltaVelocity);
            physicalObject.setVelocity(spriteVelocity);
        }

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
    public void basicCollisionOccurred(IPhysics thisObject, Sprite otherObject, Angle angleOfImpact, double coefficientOfRestitution) {
        if (isOn()) {
            double myParallel = thisObject.getVelocity().getParallelComponent(angleOfImpact);
            double myPerp = thisObject.getVelocity().getPerpComponent(angleOfImpact);
            double otherParallel = getSpriteVelocity(otherObject).getParallelComponent(angleOfImpact);
            double otherPerp = getSpriteVelocity(otherObject).getPerpComponent(angleOfImpact);

            // Uses the coefficient of restitution in a way that works properly
            // for the endpoints (0 and 1), but I'm not
            // sure about the intermediate behavior?
            Velocity newVelocity = new Velocity(-myParallel * coefficientOfRestitution + otherParallel, -myPerp
                    * coefficientOfRestitution + otherPerp, angleOfImpact);
            thisObject.setVelocity(newVelocity);
        }
    }

    public static Velocity getSpriteVelocity(Sprite sprite) {
        return new Velocity(sprite.getHorizontalSpeed(), -sprite.getVerticalSpeed());
    }

    public static void setSpriteVelocity(Sprite sprite, Velocity velocity) {
        sprite.setHorizontalSpeed(velocity.getXComponent());
        sprite.setVerticalSpeed(-velocity.getYComponent());
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
    public void applyRotationalForce(INewtonianPhysics physicalObject, Force force, Point pointOfApplication, long elapsedTime) {
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
    public void applyFriction(INewtonianPhysics physicalObject, Force force, Angle surfaceTangent, long elapsedTime) {
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
            Force friction = new Force(normalMagnitude * physicalObject.getCoefficientOfFriction(), surfaceTangent);
            friction.applyForce(physicalObject, elapsedTime);
        }
    }

}
