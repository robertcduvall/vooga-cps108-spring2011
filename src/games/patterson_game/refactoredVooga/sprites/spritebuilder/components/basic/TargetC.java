package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.basic;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ITargetable;
import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;

public class TargetC extends BasicComponent
{

    protected ITargetable myTarget;
    
    
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
    protected Object[] getFieldValues ()
    {
        return new Object[]{myTarget};
    }

    @Override
    protected void setFieldValues (Object ... fields)
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
        myTarget = (ITargetable) target;
    }

    public Object getTarget ()
    {
        return myTarget;
    }
    
    
}
