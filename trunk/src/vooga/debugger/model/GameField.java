package vooga.debugger.model;

import java.lang.reflect.Field;

/**
 * Class that holds data for field in game as well as gui components
 * 
 * @author Troy Ferrell
 * @author Austin Benesh
 */
public class GameField
{
	protected Field [] reflectionPath;
	protected String myFieldName;
	
	protected boolean active = true;
	
	public GameField(Field [] path)
	{
		reflectionPath = path;
	
		myFieldName = path[path.length -1].getName();
	}
	
	/**
	 * Get reflection path to this field through system with Game class as root
	 * @return in order field array of path
	 */
	public Field [] getPath()
	{
		return reflectionPath;
	}
	
	/**
	 * Get Field object encapsulated in this GameField
	 * @return the field
	 */
	public Field getField()
	{
		return reflectionPath[reflectionPath.length - 1];
	}
	
	/**
	 * Is GameField being displayed live on debugger
	 * @return
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * Deactivate game field from being live
	 */
	public void deactivate()
	{	
		active = false;
	}
	
	/**
	 * This method updates the GameField with the actual object instance
	 * corresponding to this field based on it's path
	 * 
	 * @param deltaTime - time since last update
	 * @param fieldInstance
	 */
	protected void updateField(long deltaTime, Object fieldInstance)
	{	
		
	}
	
	/**
	 * Compare two GameField and check for equality
	 * @param gf - GameField to compare to
	 * @return state of equality between two GameFields
	 */
	public boolean areFieldsEqual(GameField gf)
	{
		Field field1 = this.getField();
		Field field2 = gf.getField();
		
		return  field1.equals(field2);
	}
	
	
}
