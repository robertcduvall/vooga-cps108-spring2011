package vooga.util.buildable.components.predefined.basic;

import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.IComponent;

public class IntegerC extends BasicComponent
{

    protected Integer myInteger;
    
    public IntegerC(Integer s){
        myInteger = s;
    }
    
    @Override
    protected int compareTo (BasicComponent o)
    {
        return this.getInteger().compareTo(((IntegerC) o).getInteger());
    }

    private Integer getInteger ()
    {
        return myInteger;
    }

    @Override
    protected Object[] getFields ()
    {
        return new Object[]{myInteger};
    }

    @Override
    protected void setFields (Object ... fields)
    {
        myInteger = (Integer) fields[0];
    }
    

   

}
