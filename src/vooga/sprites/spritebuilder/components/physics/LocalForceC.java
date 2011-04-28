package vooga.sprites.spritebuilder.components.physics;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import vooga.physics.util.Force;
import vooga.util.buildable.components.BasicComponent;

/**
 * Used to hold the local forces that are currently being applied to the sprite.
 * 
 * @author Nathan Klug
 *
 */
public class LocalForceC extends BasicComponent {

    private Collection<Force> localForces;
    
    public LocalForceC(){
        localForces = new HashSet<Force>();
    }
    
    /**
     * Adds the given force to the collection of local forces if it is not already there.
     * @param newForce
     * @return true if the force was not already in the collection and was therefore added
     */
    public boolean addLocalForce(Force newForce){
        if (localForces.contains(newForce))
            return false;
        localForces.add(newForce);
        return true;
    }
    
    /**
     * Removes the given force from the collection of local forces if it was there.
     * @param forceToRemove
     * @return true if the force was already in the collection and was therefore removed
     */
    public boolean removeLocalForce(Force forceToRemove){
        if (!localForces.contains(forceToRemove))
            return false;
        localForces.remove(forceToRemove);
        return true;
    }
    
    public Collection<Force> getLocalForces(){
        return localForces;
    }
    
    
    @Override
    protected int compareTo(BasicComponent o) {
        return 0;
    }

    @Override
    protected Object[] getFieldValues() {
        return new Object[]{localForces};
    }

    @Override
    protected void setFieldValues(Object... fields) {
        localForces = (Collection<Force>) fields[0];
        
    }

}
