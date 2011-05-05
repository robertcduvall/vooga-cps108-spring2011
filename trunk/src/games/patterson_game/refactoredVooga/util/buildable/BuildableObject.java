package games.patterson_game.refactoredVooga.util.buildable;

import games.patterson_game.refactoredVooga.util.buildable.components.ComponentResources;
import games.patterson_game.refactoredVooga.util.buildable.components.ComponentSet;
import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;


/**
 * A buildable object designed to carry and retrieve any number of "Components"
 * 
 * @author Julian
 */
public abstract class BuildableObject extends Observable
    implements Comparable<BuildableObject>, Cloneable, IBuildable
{

    protected ComponentSet<IComponent> myComponents;
    protected boolean amActive;


    /**
     * Creates and empty BuildableObject
     */
    public BuildableObject ()
    {
        this(new IComponent[0]);
    }


    /**
     * A BuildableObject initialized with the default versions of these
     * components.
     * 
     * @param components
     */
    public BuildableObject (Class<? extends IComponent> ... components)
    {
        this();
        replaceAllComponents(components);

    }


    /**
     * A IComponent initialized with these exact components.
     * 
     * @param components
     */
    public BuildableObject (IComponent ... components)
    {
        replaceAllComponents(components);
    }


    /**
     * activate this buildable object
     */
    public void activate ()
    {
        amActive = true;
    }


    /**
     * deactivate this buildable object
     */
    public void deactivate ()
    {
        amActive = false;
    }


    /**
     * Check to see if this buildable object is active
     * 
     * @return
     */
    public boolean isActive ()
    {
        return amActive;
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#getComponents()
     */
    @Override
    public TreeSet<IComponent> getComponents ()
    {
        return myComponents;
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#getComponent(util.buildable.components.IComponent)
     */
    @Override
    public IComponent getComponent (IComponent component)
    {
        return this.getComponent(component.getClass());
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#getComponent(java.lang.Class)
     */
    @Override
    public <T extends IComponent> T getComponent (Class<T> cls)
    {
        for (IComponent mq : myComponents)
        {
            if (ComponentResources.areSameComponent(mq.getClass(),cls))
            {
                return cls.cast(mq);
            }
        }
        return null;
    }


    @Override
    public void setComponent (Class<? extends IComponent> comp, Object ... args)
    {
        if (this.carriesComponent(comp)){
            try
            {
                this.addComponent(ComponentResources.findConstructor(comp, Arrays.asList(args)).newInstance(args));
            }
            catch (Exception e)
            {
                throw new BuildException(BuildException.BAD_INPUT);
            }
        }
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#replaceAllComponents(util.buildable.components.IComponent)
     */
    @Override
    public void replaceAllComponents (IComponent ... components)
    {
        myComponents = new ComponentSet<IComponent>();
        myComponents.addAll(Arrays.asList(components));
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#replaceAllComponents(java.lang.Class)
     */
    @Override
    public void replaceAllComponents (Class<? extends IComponent> ... components)
    {
        myComponents = new ComponentSet<IComponent>();
        for (Class<? extends IComponent> c : components)
            try
            {
                myComponents.add(c.newInstance());
            }
            catch (Exception e)
            {
                throw new BuildException(BuildException.NO_DEFAULT_CONSTRUCTOR);
            }
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#setComponents(util.buildable.components.IComponent)
     */
    @Override
    public void setComponents (IComponent ... comps)
    {
        for (IComponent c : comps)
            if (!this.carriesComponent(c))
                this.addComponent(c);
            else
                this.getComponent(c).setTo(c);
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#addComponents(java.lang.Class)
     */
    @Override
    public void addComponents (Class<? extends IComponent> ... components)
    {
        for (Class<? extends IComponent> c : components)
        {
            this.addComponent(c);
        }

    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#addComponents(util.buildable.components.IComponent)
     */
    @Override
    public void addComponents (IComponent ... components)
    {
        myComponents.addAll(Arrays.asList(components));
    }


    /**
     * Adds a clone of this component to this Container
     * 
     * @param c
     */
    public void addComponent (IComponent c)
    {
        myComponents.add(c);
    }


    /**
     * Adds a default version of this component to this Container
     * 
     * @param c
     */
    public void addComponent (Class<? extends IComponent> c)
    {
        try
        {
            myComponents.add(c.newInstance());
        }
        catch (Exception e)
        {
            throw new BuildException(BuildException.EMPTY_CONSTRUCTOR_DNE);
        }
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#removeComponent(util.buildable.components.IComponent)
     */
    @Override
    public void removeComponent (IComponent ... comps)
    {
        for (IComponent c : comps)
            removeComponent(c);
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#removeComponent(util.buildable.components.IComponent)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void removeComponent (IComponent c)
    {
        removeComponent(c.getClass());
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#removeComponent(java.lang.Class)
     */
    @Override
    public boolean removeComponent (Class<? extends IComponent> ... comps)
    {
        boolean b = true;
        for (Class<? extends IComponent> c : comps)
            if (this.carriesComponent(c)) myComponents.remove(this.getComponent(c));
            else b = false;
        return b;
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#replaceComponent(util.buildable.components.IComponent, util.buildable.components.IComponent)
     */
    @Override
    public void replaceComponent (IComponent toReplace,
                                IComponent replaceWith)
    {

    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#replaceComponent(java.lang.Class, util.buildable.components.IComponent)
     */
    @Override
    public void replaceComponent (Class<? extends IComponent> toReplace,
                                IComponent replaceWith)
    {
        this.removeComponent(toReplace);
        this.addComponent(replaceWith);
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#replaceComponent(java.lang.Class, java.lang.Class)
     */
    @Override
    public void replaceComponent (Class<? extends IComponent> toReplace,
                                Class<? extends IComponent> replaceWith)
    {
        this.removeComponent(toReplace);
        this.addComponent(replaceWith);
    }


    /**
     * Returns true this Container carries a component of the same class as the
     * input component
     * 
     * @param obj
     * @return
     */
    public boolean carriesComponent (IComponent obj)
    {
        return carriesComponent(obj.getClass());
    }


    /**
     * Returns true this Container carries a component of the same class as the
     * input class
     * 
     * @param obj
     * @return
     */
    public boolean carriesComponent (Class<? extends IComponent> cls)
    {
        if (getComponent(cls) == null) return false;
        return true;
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#carriesExactComponents(util.buildable.components.IComponent)
     */
    @Override
    public boolean carriesExactComponents (IComponent ... comps)
    {
        for (IComponent c: comps){
            if (!this.carriesComponent(c.getClass()))
                return false;
            else if (!this.getComponent(c.getClass()).equals(c))
                return false;
        }
        return true;
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#carriesComponents(util.buildable.components.IComponent)
     */
    @Override
    public boolean carriesComponents (IComponent ... comps)
    {
        for (IComponent c : comps)
        {
            if (!this.carriesComponent(c)) return false;
        }
        return true;
    }


    /* (non-Javadoc)
     * @see util.buildable.IBuildable#carriesComponents(java.lang.Class)
     */
    @Override
    public boolean carriesComponents (Class<? extends IComponent> ... classes)
    {
        for (Class<? extends IComponent> c : classes)
        {
            if (!this.carriesComponent(c)) return false;
        }
        return true;
    }




    /* (non-Javadoc)
     * @see util.buildable.IBuildable#clear()
     */
    @Override
    public void clearComponents ()
    {
        myComponents.clear();
    }


    public static List<Class<? extends IComponent>> convertToClassList (List<IComponent> comps)
    {
        List<Class<? extends IComponent>> cls =
            new ArrayList<Class<? extends IComponent>>();
        for (IComponent c : comps)
            cls.add(c.getClass());
        return cls;
    }


    protected void canCompare (IBuildable o)
    {
        this.carriesComponents((IComponent[]) o.getComponents().toArray());

    }


    
    @Override
    public IBuildable clone ()
    {
        try
        {
            return this.getClass()
                       .getConstructor(TreeSet.class)
                       .newInstance(myComponents.toArray());//this needs to be an array
        }
        catch (Exception e)
        {
            throw new BuildException(BuildException.ERROR_IN_CLONING);
        }
    }

}
