package vooga.util.buildable.components.predefined.basic;

import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.IComponent;

public class VelocityC  extends BasicComponent
{
    
    protected Double[] myVelocities;
    
    VelocityC( Double ... vs){
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
        return ((Double)this.getAbsoluteVelocity()).compareTo(((VelocityC) o).getAbsoluteVelocity());
    }



    @Override
    protected Object[] getFields ()
    {
        return myVelocities;
    }



    @Override
    protected void setFields (Object ... fields)
    {
        for (int i = 0; i < myVelocities.length; i++){
            myVelocities[i] = (Double) fields[i];
        }
    }


  


}
