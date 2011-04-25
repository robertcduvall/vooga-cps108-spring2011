package vooga.collisions.intersections;

import vooga.collisions.shapes.regularShapes.IShape;
import vooga.collisions.shapes.regularShapes.*;

public abstract class IntersectionFinder<T extends IShape,S extends IShape> implements Comparable
{
	
	 public abstract Intersection getIntersection(T s1, S s2);
	 
	 public abstract boolean areIntersecting(T s1, S s2);
}