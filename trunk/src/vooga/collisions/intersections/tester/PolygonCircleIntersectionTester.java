package vooga.collisions.intersections.tester;

import java.awt.Point;
import java.awt.geom.Point2D;

import vooga.collisions.intersections.IntersectionFactory;
import vooga.collisions.shapes.collisionShapes.CollisionCircle;
import vooga.collisions.shapes.regularShapes.Circle;
import vooga.collisions.shapes.regularShapes.Polygon;

public class PolygonCircleIntersectionTester 
{
	public static void main(String[] args)
	{
		//first make the shapes (START THEM WITH BLANK CONSTUCTORS)
		Circle c1 = new Circle();
		Point2D[] points = new Point2D[]{
								new Point2D.Double(10,10),
								new Point2D.Double(20,10),
								new Point2D.Double(20,20),
								new Point2D.Double(10,20),
		};
		Polygon p= new Polygon(points);
		
		//Set the size of the shapes
		c1.setRadius(5);
		
		//Set the location of the shapes
		c1.setCenter(new Point2D.Double(15,15));
//		
//		//circle inside polygon		
//		System.out.println("Shapes are not intersecting: should return true " + IntersectionFactory.areIntersecting(c1, p));
//		
//		//circle barely touching
//		c1.setCenter(new Point2D.Double(25,25));
//		System.out.println("Circles are intersecting:  " + IntersectionFactory.areIntersecting(c1, p));
//		
//		//circle barely touching not at a vertex
//		c1.setCenter(new Point2D.Double(17,25));
//		System.out.println("Circles are intersecting:  " + IntersectionFactory.areIntersecting(c1, p));
//		
		//circle not touching
		c1.setCenter(new Point2D.Double(50,50));
		System.out.println("Circles are intersecting: (should return false) " + IntersectionFactory.areIntersecting(c1, p));
		
//		//circle crossing
//		c1.setCenter(new Point2D.Double(17,17));
//		System.out.println("Circles are intersecting: (should return false) " + IntersectionFactory.areIntersecting(c1, p));
	}
}
