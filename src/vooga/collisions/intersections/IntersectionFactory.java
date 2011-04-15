package vooga.collisions.intersections;

import java.io.File;
import java.util.TreeSet;

import vooga.collisions.CollisionException;
import vooga.collisions.shapes.regularShapes.Shape;

public class IntersectionFactory 
{
	static TreeSet<IntersectionFinder> iFSet;
	private static File INTERSECTION_RESOURCE = new File("vooga/collisions/intersections/intersectionFactory");

	static {
		//TODO: talk to resource and don't hard code this
		iFSet = buildIntersectionSet(INTERSECTION_RESOURCE );
	}
	

	private static TreeSet<IntersectionFinder> buildIntersectionSet(File resourceFile) {
		// TODO Figure out where the resourceFile is
		return null;
	}

	public Intersection getIntersection(Class<? extends Shape> c1, Class<? extends Shape> c2)
	{
		try
		{
			for(IntersectionFinder finder : iFSet)
			{
				if(finder.canApply(c1,c2) )
				{
					return finder.getIntersection(c1, c2);
				}
				else if (finder.canApply(c2, c1))
				{
					return finder.getIntersection(c2, c1);
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
