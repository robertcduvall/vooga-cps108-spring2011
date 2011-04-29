package vooga.physics;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import vooga.physics.forceBehavior.EmptyForceBehavior;
import vooga.physics.forceGenerator.AbstractForceGenerator;
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

    private Set<AbstractForceGenerator> worldForces;
    private Set<AbstractForceGenerator> activeForces;

    public PhysicsManager() {
        worldForces = new HashSet<AbstractForceGenerator>();
        activeForces = new HashSet<AbstractForceGenerator>();
    }

    /**
     * Adds a force to the collection of worldwide forces.
     * 
     * @param force
     */
    public void addGlobalForce(AbstractForceGenerator force, boolean isActive) {
        worldForces.add(force);
        setActivity(force, isActive);
    }

    /**
     * Removes a force from the collection of worldwide forces.
     * 
     * @param force
     */
    public void removeGlobalForce(AbstractForceGenerator force) {
        worldForces.remove(force);
        if (activeForces.contains(force))
            activeForces.remove(force);
    }
    
    public void setActivity(AbstractForceGenerator force, boolean activity){
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

    /**
     * Gets all of the forces in this manager
     * @return
     */
    public Collection<AbstractForceGenerator> getWorldForces() {
        return worldForces;
    }
    
    /**
     * Gets all of the forces in this manager
     * @return
     */
    public Collection<AbstractForceGenerator> getActiveWorldForces() {
        return activeForces;
    }


    
    public void clear(){
        worldForces = new HashSet<AbstractForceGenerator>();
        activeForces = new HashSet<AbstractForceGenerator>();
    }
}
