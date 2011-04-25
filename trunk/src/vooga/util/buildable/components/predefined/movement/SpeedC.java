package vooga.util.buildable.components.predefined.movement;

import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.IComponent;

public class SpeedC  extends BasicComponent 
{
    
    protected Double[] myVelocities;
    
    SpeedC( Double ... vs){
        myVelocities = vs;
    }
    


    public double getAbsoluteVelocity ()
    {
        double sum = 0;
        for (double v : myVelocities)
            sum +=Math.pow(v,2);
        return Math.sqrt(sum);
    }



    @Override
    protected int compareTo (BasicComponent o)
    {
        return ((Double)this.getAbsoluteVelocity()).compareTo(((SpeedC) o).getAbsoluteVelocity());
    }



    @Override
    protected Object[] getFieldValues ()
    {
        return myVelocities;
    }



    @Override
    protected void setFieldValues (Object ... fields)
    {
        for (int i = 0; i < myVelocities.length; i++){
            myVelocities[i] = (Double) fields[i];
        }
    }


  


}
