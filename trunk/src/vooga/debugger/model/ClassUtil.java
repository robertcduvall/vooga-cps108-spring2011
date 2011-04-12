package vooga.debugger.model;

import java.lang.reflect.Field;
import java.util.AbstractCollection;

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
