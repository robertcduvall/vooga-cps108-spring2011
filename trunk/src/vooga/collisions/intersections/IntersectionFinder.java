package vooga.collisions.intersections;

import vooga.collisions.shapes.regularShapes.Shape;

public abstract class IntersectionFinder {
	
	abstract boolean canApply(Class<? extends Shape> c1, Class<? extends Shape> c2) ;
	
	boolean canApply(Shape s1, Shape s2)
	{
		return this.canApply(s1.getClass(), s2.getClass());
	}

	 public abstract Intersection getIntersection(Class<? extends Shape> c1, Class<? extends Shape> c2);
	 
	 
}