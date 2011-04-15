package vooga.collisions.intersections;

import vooga.collisions.shapes.regularShapes.*;

public class PolygonPolygonFinder extends IntersectionFinder
{

	@Override
	boolean canApply(Class<? extends Shape> c1, Class<? extends Shape> c2) {
		if (c1.isInstance(Polygon.class) && c2.isInstance(Polygon.class))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public Intersection getIntersection(Class<? extends Shape> c1, Class<? extends Shape> c2) {
		// TODO Auto-generated method stub
		return null;
	}

}
