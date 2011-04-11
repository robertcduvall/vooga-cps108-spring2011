package vooga.util.buildable.components.predefined.basic;

import vooga.util.buildable.components.BasicComponent;


public class DoubleC extends BasicComponent
{
   protected Double myDouble;
    
    public DoubleC(Double s){
        myDouble = s;
    }
    
    @Override
    protected int compareTo (BasicComponent o)
    {
        return this.getDouble().compareTo(((DoubleC) o).getDouble());
    }

    private Double getDouble ()
    {
        return myDouble;
    }

    @Override
    protected Object[] getFields ()
    {
        return new Object[]{myDouble};
    }

    @Override
    protected void setFields (Object ... fields)
    {
        myDouble = (Double) fields[0];
    }

   


}
