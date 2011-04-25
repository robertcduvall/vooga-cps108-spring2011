package vooga.collisions.intersections;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.TreeSet;

import vooga.collisions.CollisionException;
import vooga.collisions.shapes.regularShapes.Circle;
import vooga.collisions.shapes.regularShapes.IShape;
import vooga.collisions.shapes.regularShapes.Shape;
import vooga.reflection.Reflection;

public class IntersectionFactory 
{
	static TreeSet<IntersectionFinder<?, ?>> myFinderSet;
	private static String INTERSECTION_RESOURCE = "vooga.collisions.intersections.resources.intersectionFactory";

	static
	{
		//TODO: talk to resource and don't hard code this
		buildIntersectionSet(INTERSECTION_RESOURCE);
	}

	private static void buildIntersectionSet(String resourceFile) {
		myFinderSet = new TreeSet<IntersectionFinder<?,?>>();
		ResourceBundle rb = ResourceBundle.getBundle(resourceFile);
		for(String s: rb.keySet()){
			myFinderSet.add((IntersectionFinder<?, ?>) Reflection.createInstance((String) rb.getObject(s)));
		}
	}

	public static Intersection getIntersection(IShape s1,IShape s2)
	{
		return (Intersection) applyOrderGenericIntersectionMethod("getIntersection", s1, s2);
	}

	public static boolean areIntersecting(IShape s1,IShape s2)
	{
		return (Boolean) applyOrderGenericIntersectionMethod("areIntersecting", s1, s2);
	}

	
	private static <T extends IShape, S extends IShape>  Object applyOrderGenericIntersectionMethod(String methodString, T s1,S s2)
	{
		
		
		
		try
		{
			for(IntersectionFinder<?, ?> finder : myFinderSet)
			{
				Method m = finder.getClass().getMethod(methodString, IShape.class, IShape.class);
				if(finder.canApply(s1.getClass(), s2.getClass()))
				{
					
					return m.invoke(finder, s1, s2);
				}
				else if (finder.canApply(s2.getClass(), s1.getClass()))
				{
					return m.invoke(finder, s2, s1);
				}
			}
		}
		catch(Exception e)
		{
			throw new CollisionException(CollisionException.NO_SUCH_METHOD);
		}
		throw new CollisionException(CollisionException.BAD_SYNTAX);
	}
}
