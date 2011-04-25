package vooga.physics;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.util.Force;
import vooga.physics.util.VectorField;
import vooga.physics.util.Velocity;
import java.awt.Point;
import java.util.HashSet;
import vooga.util.math.Angle;

/**
 * The base of all other physics engines. Provides a structure for defining
 * custom behavior in response to forces, fields, and collisions
 * 
 * @author Nathan Klug
 * 
 */
public class PhysicsManager {

    private Set<Force> worldForces;
    private Set<VectorField> vectorFields;
    private Set<Force> activeForces;
    private Set<VectorField> activeFields;

    public PhysicsManager() {
        worldForces = new HashSet<Force>();
        vectorFields = new HashSet<VectorField>();
        activeForces = new HashSet<Force>();
        activeFields = new HashSet<VectorField>();
    }

    /**
     * Adds a force to the collection of worldwide forces.
     * 
     * @param force
     */
    public void addGlobalForce(Force force, boolean isActive) {
        worldForces.add(force);
        setActivity(force, isActive);
    }

    /**
     * Removes a force from the collection of worldwide forces.
     * 
     * @param force
     */
    public void removeGlobalForce(Force force) {
        worldForces.remove(force);
        if (activeForces.contains(force))
            activeForces.remove(force);
    }

    /**
     * Adds a point field to the collection of worldwide fields
     * 
     * @param field
     */
    public void addGlobalPointField(VectorField field, boolean isActive) {
        vectorFields.add(field);
        setActivity(field, isActive);
    }

    /**
     * Removes a point field from the collection of worldwide point fields.
     * 
     * @param field
     */
    public void removeGlobalPointField(VectorField field) {
        vectorFields.remove(field);
        if (activeFields.contains(field))
            activeFields.remove(field);
    }
    
    public void setActivity(Force force, boolean activity){
        if (!worldForces.contains(force)){
            //Throw warning?
        }
        else {
            if (activity)
                activeForces.add(force);
            else
                activeForces.remove(force);
        }  
    }
    
    public void setActivity(VectorField field, boolean activity){
        if (!vectorFields.contains(field)){
            //Throw warning?
        }
        else {
            if (activity)
                vectorFields.add(field);
            else
                vectorFields.remove(field);
        }  
    }

    /**
     * Gets all of the forces in this manager
     * @return
     */
    public Collection<Force> getWorldForces() {
        return worldForces;
    }
    
    /**
     * Gets all of the forces in this manager
     * @return
     */
    public Collection<Force> getActiveWorldForces() {
        return activeForces;
    }

    /**
     * Gets all of the vector fields in this manager
     * @return
     */
    public Collection<VectorField> getVectorFields() {
        return vectorFields;
    }
    
    /**
     * Gets all of the vector fields in this manager
     * @return
     */
    public Collection<VectorField> getActiveVectorFields() {
        return activeFields;
    }
    
    public void clear(){
        worldForces = new HashSet<Force>();
        vectorFields = new HashSet<VectorField>();
        activeForces = new HashSet<Force>();
        activeFields = new HashSet<VectorField>();
    }
}
