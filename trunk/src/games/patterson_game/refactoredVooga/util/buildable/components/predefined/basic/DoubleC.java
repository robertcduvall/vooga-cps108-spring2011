package games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic;

import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;


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

    protected Double getDouble ()
    {
        return myDouble;
    }

    @Override
    protected Object[] getFieldValues ()
    {
        return new Object[]{myDouble};
    }

    @Override
    protected void setFieldValues (Object ... fields)
    {
        myDouble = (Double) fields[0];
    }

   


}
