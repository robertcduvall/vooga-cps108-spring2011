package games.patterson_game.refactoredVooga.collisions.intersections;

import games.patterson_game.refactoredVooga.collisions.CollisionException;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Circle;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.IShape;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Shape;
import games.patterson_game.refactoredVooga.reflection.Reflection;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.TreeSet;


public class IntersectionFactory 
{
	static TreeSet<IntersectionFinder<?, ?>> myFinderSet;
	private static String INTERSECTION_RESOURCE = "games.patterson_game.refactoredVooga.collisions.intersections.resources.intersectionFactory";

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
