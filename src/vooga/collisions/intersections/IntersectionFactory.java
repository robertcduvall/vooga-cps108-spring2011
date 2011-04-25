package vooga.collisions.intersections;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.TreeSet;

import vooga.collisions.CollisionException;
import vooga.collisions.shapes.regularShapes.IShape;
import vooga.collisions.shapes.regularShapes.Shape;
import vooga.reflection.Reflection;

public class IntersectionFactory 
{
	static TreeSet<IntersectionFinder> myFinderSet;
	private static String INTERSECTION_RESOURCE = "vooga.collisions.intersections.resources.intersectionFactory";

	static
	{
		//TODO: talk to resource and don't hard code this
		buildIntersectionSet(INTERSECTION_RESOURCE);
	}

	private static void buildIntersectionSet(String resourceFile) {
		myFinderSet = new TreeSet<IntersectionFinder>();
		ResourceBundle rb = ResourceBundle.getBundle(resourceFile);
		for(String s: rb.keySet()){
			myFinderSet.add((IntersectionFinder) Reflection.createInstance((String) rb.getObject(s)));
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

	
	private static Object applyOrderGenericIntersectionMethod(String methodString, IShape s1,IShape s2)
	{
		try
		{
			Method m = IntersectionFinder.class.getMethod(methodString, IShape.class, IShape.class);
			for(IntersectionFinder finder : myFinderSet)
			{
				if(finder.canApply(s1,s2))
				{
					return m.invoke(finder, s1, s2);
				}
				else if (finder.canApply(s2, s1))
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
