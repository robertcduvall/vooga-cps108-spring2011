package vooga.debugger.model;
import java.lang.reflect.Field;


/**
 * @author Troy Ferrell
 */

public interface DebuggerParser 
{
	public Field[] getValidFieldsFor(Class<?> testClass);
	
	public GameField createGameField(Object gameObj);
}
