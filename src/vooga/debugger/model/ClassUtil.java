package vooga.debugger.model;

/**
 * Deprecated Class, keeping around in case we need it later.
 * 
 * @author Austin Benesh
 */
public class ClassUtil
{
	public static boolean isIterable(Class<?> rootClass)
	{
		if(rootClass == null)
			return false;
		if(rootClass.getName().equals("java.util.AbstractCollection"))
			return true;
		return isIterable(rootClass.getSuperclass());
	}
	public static boolean isMappable(Class<?> rootClass)
	{
		if(rootClass == null)
			return false;
		if(rootClass.getName().equals("java.util.AbstractMap"))
			return true;
		return isMappable(rootClass.getSuperclass());
	}
	
}
