package vooga.physics;

import java.util.Collection;

import vooga.physics.customBehavior.IPhysicsCustomCollide;
import vooga.physics.customBehavior.IPhysicsCustomField;
import vooga.physics.customBehavior.IPhysicsCustomForce;
import vooga.physics.util.Force;
import vooga.physics.util.IPhysicsToggle;
import vooga.physics.util.IPointField;
import java.awt.Point;
import java.util.HashSet;
import vooga.util.math.Angle;

/**
 * The base of all other physics engines. Provides a structure for defining custom behavior
 * in response to forces, fields, and collisions
 * 
 * @author Nathan Klug
 *
 */
public class BasePhysicsEngine implements IPhysicsToggle {

    private Collection<Force> worldForces;
    private Collection<IPointField> pointFields;
    private boolean isOn;

    public BasePhysicsEngine() {
        worldForces = new HashSet<Force>();
        pointFields = new HashSet<IPointField>();
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
    public void addGlobalPointField(IPointField field) {
        pointFields.add(field);
    }

    /**
     * Removes a point field from the collection of worldwide point fields.
     * 
     * @param field
     */
    public void removeGlobalForce(IPointField field) {
        pointFields.remove(field);
    }

    /**
     * Applies all of the worldwide forces to an object, using the applyForce method
     * 
     * @param <T>
     * 
     * @param sprite
     * @param elapsedTime
     */
    public <T> void applyWorldForces(T object, long elapsedTime) {
        if (isOn) {
            for (Force f : worldForces) {
                applyForce(object, f, elapsedTime);
            }
        }
    }

    /**
     * Determines if an object defines custom behavior for responding to the given force.
     * 
     * @param <T>
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     * 
     * @return a boolean representing if custom behavior was used
     */
    public <T> boolean applyForce(T object, Force force, long elapsedTime) {
        if (IPhysicsCustomForce.class.isAssignableFrom(object.getClass())) {
            ((IPhysicsCustomForce) object).applyForce(force, elapsedTime);
            return true;
        }
        return false;
    }

    /**
     * Applies all of the worldwide point fields to an object
     * 
     * @param <T>
     * @param affectedObject
     * @param elapsedTime
     */
    public <T> void applyPointFields(T object, long elapsedTime) {
        if (isOn) {
            for (IPointField f : pointFields) {
                applyField(object, f, elapsedTime);
            }
        }
    }

    /**
     * Determines if an object defines custom behavior for responding to the given field.
     * 
     * @param <T>
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     * 
     * @return a boolean representing if custom behavior was used
     */
    public <T> boolean applyField(T object, IPointField field, long elapsedTime) {
        if (IPhysicsCustomField.class.isAssignableFrom(object.getClass())) {
            ((IPhysicsCustomField) object).applyField(field, elapsedTime);
            return true;
        }
        return false;
    }

    /**
     * General collision method. Tells the two physical objects that a collision
     * occurred.
     * 
     * TODO: define a default interface to use for the other object in the collision,
     * similar to in VoogaPhysicsMediator. Otherwise, may generate compile-time errors,
     * due to ambiguous method calls.
     * 
     * @param object1
     * @param object2
     * @param pointOfCollision
     * @param angleOfImpact
     * @param coefficientOfRestitution
     */
    public void collision(Object object1, Object object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (isOn()) {
            Class<?>[] firstObjectInterfaces = object1.getClass().getInterfaces();
            Class<?>[] secondObjectInterfaces = object2.getClass().getInterfaces();
            
            for (Class<?> c : firstObjectInterfaces) {
                applyCollision(c.getClass().cast(object1), object2, angleOfImpact, pointOfImpact,
                        coefficientOfRestitution);
            }
            
            for (Class<?> c : secondObjectInterfaces) {
                applyCollision(c.getClass().cast(object2), object1, angleOfImpact, pointOfImpact,
                        coefficientOfRestitution);
            }
        }
    }

    /**
     * Determines if an object defines custom behavior for responding to the given collision.
     * 
     * @param <T>
     * 
     * @param thisObject the object for which we are calculating the result of the collision
     * @param otherObject the other object in the collision
     * @param angleOfImpact the angle that the collision is occurring at. The angle representing the tangent vector to the point of intersection.
     * @param pointOfImpact the point at which the collision occurs
     * @param coefficientOfRestitution the coefficient representing the elasticity of the collision. 0 = inelastic, 1 = elastic
     * 
     * @return a boolean representing if custom behavior was used
     */
    public <T> boolean applyCollision(T thisObject, Object otherObject, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution) {
        if (IPhysicsCustomCollide.class.isAssignableFrom(thisObject.getClass())) {
            ((IPhysicsCustomCollide) thisObject).collisionOccurred(otherObject, angleOfImpact, pointOfImpact,
                    coefficientOfRestitution);
            return true;
        }
        return false;
    }

    /**
     * Is physics on?
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * set physics on or off based on the parameter
     */
    @Override
    public void turnPhysicsOnOff(boolean isOn) {
        this.isOn = isOn;
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
     * Turns physics on if it is off and turns physics off if it is on
     */
    public void togglePhysicsOnOff() {
        isOn = !isOn;
    }

}
