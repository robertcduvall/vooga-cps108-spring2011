package vooga.util.buildable.components;

import vooga.util.buildable.BuildException;




public abstract class BasicComponent implements IComponent
{

    public BasicComponent ()
    {}


    public BasicComponent (BasicComponent bc)
    {
        this.setTo(bc);
    }


    @Override
    public void setTo (IComponent c)
    {
        this.CheckLikeness(c);
        this.setFields(((BasicComponent) c).getFields());

    }

    
    @Override
    public void setTo (Object ... args)
    {
        this.setFields(args);
    }


    @Override
    public int compareTo (IComponent o)
    {
        CheckLikeness(o);
        return this.compareTo((BasicComponent) o);
    }


    /**
     * @param o
     */
    private void CheckLikeness (IComponent o)
    {
        if (!ComponentResources.areSameComponent(this, o)) 
            throw new BuildException(BuildException.DIFFERENT_COMPONENTS);
    }


    @Override
    public IComponent clone ()
    {
        try
        {
            return this.getClass()
                       .getConstructor(this.getClass())
                       .newInstance(this);
        }
        catch (Exception e)
        {
            throw new BuildException(BuildException.ERROR_IN_CLONING);
        }
    }


    @Override
    public final boolean equals (IComponent other)
    {
        CheckLikeness(other);
        return equals((BasicComponent) other);
    }


    abstract protected int compareTo (BasicComponent o);


    public boolean equals (BasicComponent bc){
        for (Object t : this.getFields()){
            for (Object o : bc.getFields()){
                if(!t.equals(o))
                    return false;
            }
        }
        return true;
    }


    protected abstract Object[] getFields ();

    protected abstract void setFields (Object ... fields);

}
