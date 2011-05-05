package games.patterson_game.refactoredVooga.util.buildable.components.predefined.basic;

import games.patterson_game.refactoredVooga.util.buildable.components.BasicComponent;
import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.util.jar.Attributes.Name;


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
    protected Object[] getFieldValues ()
    {
        return new Object[]{myString};
    }

    @Override
    protected void setFieldValues (Object ... fields)
    {
        myString = (String) fields[0];
    }

   
  
}
