package vooga.util.math;

import vooga.collisions.shapes.regularShapes.Circle;
import vooga.collisions.shapes.regularShapes.Shape;

public class ShapeMathRefactor 
{
	public static double getDistanceToCenter(Shape s1, Shape s2)
    {
    	return s1.getCenter().distance(s2.getCenter());
    }
	
    public static double findDirectionToCenter(Shape s1, Shape s2)
    {
    	return LineMathRefactor.findDirection(s1.getCenter().getX(), s1.getCenter().getY(), s2.getCenter().getX(), s1.getCenter().getY());
    }
    
    public static double getDiffRadius(Circle c1, Circle c2)
    {
    	return (Math.pow(c1.getRadius(), 2)-Math.pow(c2.getRadius(), 2));
    }
}
