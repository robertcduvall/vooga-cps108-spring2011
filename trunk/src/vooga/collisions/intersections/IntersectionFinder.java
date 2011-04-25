package vooga.collisions.intersections;

import vooga.collisions.shapes.regularShapes.*;

public abstract class IntersectionFinder<T extends IShape,S extends IShape> implements Comparable
{
	
	protected Class<T> c1;
	protected Class<S> c2;
	
	 public abstract Intersection getIntersection(T s1, S s2);
	 
	 public abstract boolean areIntersecting(T s1, S s2);

	public boolean canApply(Class<? extends IShape> class1,
							Class<? extends IShape> class2) {
		
				
		return checkRecursiveInstance(class1, c1) && checkRecursiveInstance(class2, c2);
	}

	private boolean checkRecursiveInstance(Class<?> class2, Class<?> test) {
		Class<?> superclass = class2.getSuperclass();
		if (superclass.equals(Shape.class)) return false;
		else if(superclass.equals(test)) return true;
		
		return checkRecursiveInstance(superclass, test);
	}
	 
	 
}