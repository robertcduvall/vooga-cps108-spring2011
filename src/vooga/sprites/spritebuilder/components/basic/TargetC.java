package vooga.sprites.spritebuilder.components.basic;

import vooga.sprites.improvedsprites.interfaces.ITargetable;
import vooga.util.buildable.components.BasicComponent;

public class TargetC extends BasicComponent
{

    ITargetable myTarget;
    
    
    public TargetC()
    {
        super();
        myTarget = null;
    }
    
    public TargetC(ITargetable t){
        myTarget = t;
    }
    
    @Override
    protected int compareTo (BasicComponent o)
    {
        // TODO define compare for Target?
        return 0;
    }

    @Override
    protected Object[] getFields ()
    {
        return new Object[]{myTarget};
    }

    @Override
    protected void setFields (Object ... fields)
    {
        myTarget = (ITargetable) fields[0];
    }

    public boolean isNull(){
        return myTarget == null;
    }
    
    public void clear(){
        myTarget = null;
    }

    public void setTarget (ITargetable target)
    {
        myTarget = target;
    }

    public ITargetable getTarget ()
    {
        return myTarget;
    }
    
    
}
