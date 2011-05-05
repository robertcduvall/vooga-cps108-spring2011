package games.patterson_game.refactoredVooga.util.buildable.components;


/**
 * An interface used to create a generic "Component" which can be then used as a quality in some object without
 * specific casting.
 * @author Julian
 *
 */
public interface IComponent extends Comparable<IComponent>
{

    /**
     * Sets this component to the input qualities characteristics. Must be the
     * same component class
     * 
     * @param c
     */
    public abstract void setTo (IComponent c);
    
    /**
     * Sets this component to the input characteristics. Must be the
     * correct characteristics to satisfy one constructor in the component.
     * input must be in same order as constructor.
     * 
     * @param c
     */
    public abstract void setTo (Object ... args);
    


    public abstract boolean equals (IComponent c);

}
