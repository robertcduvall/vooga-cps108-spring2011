package vooga.collisions.intersections;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.TreeSet;

import vooga.collisions.CollisionException;
import vooga.collisions.shapes.regularShapes.IShape;
import vooga.collisions.shapes.regularShapes.Shape;
import vooga.levels.example.reflection.Reflection;

public class IntersectionFactory 
{
	static TreeSet<IntersectionFinder> myFinderSet;
	private static String INTERSECTION_RESOURCE = "vooga.collisions.intersections.resources.intersectionFactory";

	static {
		//TODO: talk to resource and don't hard code this
		myFinderSet = buildIntersectionSet(INTERSECTION_RESOURCE );
	}
	

	private static TreeSet<IntersectionFinder> buildIntersectionSet(String resourceFile) {
	    myFinderSet = new TreeSet<IntersectionFinder>();
		ResourceBundle rb = ResourceBundle.getBundle(resourceFile);
	    for(String s: rb.keySet()){
	        myFinderSet.add((IntersectionFinder) Reflection.createInstance((String) rb.getObject(s)));
	    }
	    
		return null;
	}

	public static Intersection getIntersection(IShape s1,IShape s2)
	{
		return (Intersection) applyOrderGenericIntersectionMethod("getIntersection", s1, s2);
	}
	
	public static boolean areIntersecting(IShape s1,IShape s2){
	    return (Boolean) applyOrderGenericIntersectionMethod("areIntersecting", s1, s2);
	}
	
	
	private static Object applyOrderGenericIntersectionMethod(String methodString, IShape s1,IShape s2)
	{
	    try
        {
	        Method m = IntersectionFactory.class.getMethod(methodString, s1.getClass(), s2.getClass());
            for(IntersectionFinder finder : myFinderSet)
            {
                if(finder.canApply(s1,s2) )
                {
                    return m.invoke(null, s1, s2);
                }
                else if (finder.canApply(s2, s1))
                {
                    return m.invoke(null, s2, s1);
                }
            }
        }
        catch(Exception e)
        {
            throw new CollisionException(CollisionException.NO_SUCH_CONSTRUCTOR);
        }
        throw new CollisionException(CollisionException.BAD_SYNTAX);
	}
}
