package vooga.debugger.model;
import java.lang.reflect.Field;
import java.util.TreeMap;

import org.w3c.dom.Node;


/**
 * @author Troy Ferrell
 */

public abstract class DebuggerParser 
{
	public TreeMap<String, Node> fieldMap;
	
	/**
	 * Returns valid fields for given class parameter
	 * 
	 * @param testClass - class to check
	 * @param showAll - boolean flag
	 * @return Field[] - valid Fields to display for given class
	 */
	public abstract Field[] getValidFieldsFor(Class<?> testClass, boolean showAll);
	
	/**
	 * Creates a GameField with input Object
	 * 
	 * @param gameObj - object to construct game field around
	 * @return GameField - constructed GameField object
	 */
	public abstract GameField createGameField(Object gameObj);
}
