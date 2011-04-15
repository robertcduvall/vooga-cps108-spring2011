package vooga.collisions.intersections;

import vooga.collisions.shapes.regularShapes.IShape;
import vooga.collisions.shapes.regularShapes.Shape;

public abstract class IntersectionFinder {
	
	abstract boolean canApply(Class<? extends IShape> class1, Class<? extends IShape> class2) ;
	
	boolean canApply(IShape s1, IShape s2)
	{
		return this.canApply(s1.getClass(), s2.getClass());
	}

	 public abstract Intersection getIntersection(IShape s1, IShape s2);
	 
	 public abstract boolean areIntersecting(IShape s1, IShape s2);
	 
}