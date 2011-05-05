package vooga.collisions.intersections;

import java.awt.geom.Point2D;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
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

	/**
	 * Gets the angle between shape s1 and the average of the intersection points with s2.
	 * @author Sterling Dorminey
	 */
	public static double getIntersectionAngle(IShape s1, IShape s2) {
		Intersection intersection = getIntersection(s1, s2);
		Point2D pointB = intersection.getAverageIntersection();
		Point2D pointA = s1.getCenter();
		double slope = (pointB.getY()-pointA.getY())/(pointB.getX()-pointA.getX());
		return Math.atan(slope);
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
		catch(InvocationTargetException e) {
			e.getTargetException().printStackTrace();
			throw new CollisionException(CollisionException.NO_SUCH_METHOD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new CollisionException(CollisionException.NO_SUCH_METHOD);
		}
		throw new CollisionException(CollisionException.BAD_SYNTAX);
	}
}
