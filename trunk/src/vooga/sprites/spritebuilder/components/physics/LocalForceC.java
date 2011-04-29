package vooga.sprites.spritebuilder.components.physics;



import java.util.Collection;
import java.util.HashSet;
import vooga.physics.forceGenerator.AbstractForceGenerator;
import vooga.util.buildable.components.BasicComponent;

/**
 * Used to hold the local forces that are currently being applied to the sprite.
 * 
 * @author Nathan Klug
 *
 */
public class LocalForceC extends BasicComponent {

    private Collection<AbstractForceGenerator> localForces;
    
    public LocalForceC(){
        localForces = new HashSet<AbstractForceGenerator>();
    }
    
    /**
     * Adds the given force to the collection of local forces if it is not already there.
     * @param newForce
     * @return true if the force was not already in the collection and was therefore added
     */
    public boolean addLocalForce(AbstractForceGenerator newForce){
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
    public boolean removeLocalForce(AbstractForceGenerator forceToRemove){
        if (!localForces.contains(forceToRemove))
            return false;
        localForces.remove(forceToRemove);
        return true;
    }
    
    public Collection<AbstractForceGenerator> getLocalForces(){
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
        localForces = (Collection<AbstractForceGenerator>) fields[0];
        
    }

}
