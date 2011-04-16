package vooga.physics.engine;

import java.util.Collection;
import vooga.physics.util.Force;
import java.awt.Point;
import java.util.HashSet;
import vooga.physics.interfaces.IPhysicsCollider;
import vooga.physics.interfaces.IPointField;
import vooga.physics.interfaces.IPhysicsToggle;
import vooga.util.math.Angle;

public abstract class AbstractPhysicsEngine implements IPhysicsToggle {

    private Collection<Force> worldForces;
    private Collection<IPointField> pointFields;
    private boolean isOn;

    public AbstractPhysicsEngine() {
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
     * Applies all of the worldwide forces to a sprite. This only applies the
     * MassProportionalForces, such as gravity, which do not need physical
     * properties like mass. Do not use this method if your sprite has a
     * physical nature, or else these forces will be applied twice
     * 
     * TODO: should we change it so applying the world forces to a physical
     * object does not apply the mass proportional forces?
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
     * Applies an external force to an object.
     * 
     * @param <T>
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     */
    public <T> void applyForce(T object, Force f, long elapsedTime){
        
    }

    /**
     * Applies all of the worldwide point fields to something which has that
     * same field
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
     * Applies an external field to an IPhysics object.
     * 
     * @param <T>
     * 
     * @param physicalObject
     * @param field
     * @param elapsedTime
     */
    public <T> void applyField(T object, IPointField field, long elapsedTime){
        
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
    public void collision(Object object1, Object object2, Angle angleOfImpact, Point pointOfImpact, double coefficientOfRestitution){
        if (isOn) {
            if (IPhysicsCollider.class.isAssignableFrom(object1.getClass()))
                ((IPhysicsCollider)object1).collisionOccurred(object2, angleOfImpact, pointOfImpact, coefficientOfRestitution);
            if (IPhysicsCollider.class.isAssignableFrom(object2.getClass()))
                ((IPhysicsCollider)object1).collisionOccurred(object1, angleOfImpact, pointOfImpact, coefficientOfRestitution);
        }
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
