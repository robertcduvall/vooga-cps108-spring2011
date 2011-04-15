package vooga.debugger.model;
import java.lang.reflect.Field;


/**
 * @author Troy Ferrell
 */

public interface DebuggerParser 
{
	/**
	 * Returns valid fields for given class parameter
	 * 
	 * @param testClass - class to check
	 * @param showAll - boolean flag
	 * @return Field[] - valid Fields to display for given class
	 */
	public Field[] getValidFieldsFor(Class<?> testClass, boolean showAll);
	
	/**
	 * Creates a GameField with input Object
	 * 
	 * @param gameObj - object to construct game field around
	 * @return GameField - constructed GameField object
	 */
	public GameField createGameField(Object gameObj);
}
