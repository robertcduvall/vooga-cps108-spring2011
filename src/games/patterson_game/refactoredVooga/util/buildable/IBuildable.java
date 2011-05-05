package games.patterson_game.refactoredVooga.util.buildable;

import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.util.TreeSet;


public interface IBuildable extends Cloneable
{

    /**
     * Returns this Container's list of components
     * 
     * @return
     */
    public abstract TreeSet<IComponent> getComponents ();


    /**
     * Retrieves the component contained in this wrapper that corresponds to in
     * the input IComponent's class
     * 
     * @param component
     * @return
     */
    public abstract <T extends IComponent> T getComponent (T component);


    /**
     * Retrieves the first component contained in this wrapper that corresponds to
     * in this Class<? extends IComponent>
     * 
     * @param cls
     * @return
     */
    public abstract <T extends IComponent> T getComponent (Class<T> cls);


    /**
     * Sets this buildable object's components to an exact clone of the input
     * 
     * @param components
     */
    public abstract void replaceAllComponents (IComponent ... components);


    /**
     * Sets this buildable object's components to an exact clone of the input
     * 
     * @param components
     */
    public abstract void replaceAllComponents (Class<? extends IComponent> ... components);


    /**
     * Sets this selection of the buildable object's components to the default of all
     * the input component classes
     * 
     * @param components
     */
    public abstract void setComponents (IComponent ... comps);
    
    /**
     * Sets the passed component type to this value. If component type is not present, adds a new 
     * versiomn of that component to this buildable
     * @param val
     * @param comp
     */
    public abstract void setComponent (Class<? extends IComponent> comp, Object ... val);



    /**
     * Adds a default version of each component in this list to this Container
     * 
     * @param c
     */
    public abstract void addComponents (Class<? extends IComponent> ... components);


    /**
     * Adds a default version of each component in this list to this Container
     * 
     * @param c
     */
    public abstract void addComponents (IComponent ... components);


    /**
     * Remove first instance of these components from this Container
     * 
     * @param c
     */
    public abstract void removeComponent (IComponent ... comps);


    /**
     * remove a single component from this buildable object. Targeting by CLASS of input
     * 
     * @param c
     */
    @SuppressWarnings("unchecked")
    public abstract void removeComponent (IComponent c);


    /**
     * Remove the first instance of the component of this type from this
     * Container. returns true if buildable object held all components removed
     * 
     * @param c
     */
    public abstract boolean removeComponent (Class<? extends IComponent> ... comps);


    /**
     * replaces the first component, toReplace, with a clone of the second
     * component, replaceWith
     * 
     * @param toReplace = component to be replaced
     * @param replaceWith = component to add
     */
    public abstract void replaceComponent (IComponent toReplace,
                                           IComponent replaceWith);


    /**
     * replaces the first component, toReplace, with a clone of the second
     * component, replaceWith
     * 
     * @param toReplace = component to be replaced
     * @param replaceWith = component to add
     */
    public abstract void replaceComponent (Class<? extends IComponent> toReplace,
                                           IComponent replaceWith);


    /**
     * replaces the first component (retrieved by class), toReplace, with second
     * component, replaceWith
     * 
     * @param toReplace = component to be replaced
     * @param replaceWith = component to add
     */
    public abstract void replaceComponent (Class<? extends IComponent> toReplace,
                                           Class<? extends IComponent> replaceWith);


    /**
     * Returns true this Container carries a component of the same class AND it
     * exactly equals the input
     * 
     * @param obj
     * @return
     */
    public abstract boolean carriesExactComponents (IComponent ... comps);


    /**
     * Returns true this Container carries all component of the same class as the
     * input classes
     * 
     * @param obj
     * @return
     */
    public abstract boolean carriesComponents (IComponent ... comps);


    /**
     * Returns true this Container carries all component of the same class as the
     * input classes
     * 
     * @param obj
     * @return
     */
    public abstract boolean carriesComponents (Class<? extends IComponent> ... classes);


    /**
     * removes all components from this wrapper
     * 
     * @return
     */
    public abstract void clearComponents ();





}
