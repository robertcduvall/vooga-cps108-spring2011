package vooga.util.buildable.components.predefined.basic;

import java.util.jar.Attributes.Name;
import vooga.util.buildable.components.BasicComponent;
import vooga.util.buildable.components.IComponent;


public class StringC extends BasicComponent 
{

    protected String myString;
    
    public StringC(String s){
        myString = s;
    }
    
    @Override
    protected int compareTo (BasicComponent o)
    {
        return this.getString().compareTo(((StringC) o).getString());
    }

    private String getString ()
    {
        return myString;
    }

    @Override
    protected Object[] getFields ()
    {
        return new Object[]{myString};
    }

    @Override
    protected void setFields (Object ... fields)
    {
        myString = (String) fields[0];
    }

   
  
}
