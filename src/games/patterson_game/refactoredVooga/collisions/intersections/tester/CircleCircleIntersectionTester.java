package games.patterson_game.refactoredVooga.collisions.intersections.tester;

import games.patterson_game.refactoredVooga.collisions.intersections.IntersectionFactory;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.CollisionCircle;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Circle;
import java.awt.geom.Point2D;


public class CircleCircleIntersectionTester 
{
	public static void main(String[] args)
	{
		//first make the shapes (START THEM WITH BLANK CONSTUCTORS)
		Circle c1 = new Circle();
		Circle c2 = new Circle();
		
		//Set the size of the shapes
		c1.setRadius(10);
		c2.setRadius(10);
		
		//Set the location of the shapes
		c1.setCenter(new Point2D.Double(10,10));
		c2.setCenter(new Point2D.Double(50,50));
		
		//IntersectionFactory intFactory = new IntersectionFactory();
		System.out.println("Circles are not intersecting: (should return true) " + !IntersectionFactory.areIntersecting(c1, c2));
		
		c1.setCenter(new Point2D.Double(60,60));
		System.out.println("Circles are intersecting: (should return true) " + IntersectionFactory.areIntersecting(c1, c2));
		
		c1.setRadius(3);
		c1.setCenter(new Point2D.Double(50,50));
		System.out.println("Circles are intersecting: (should return true) " + IntersectionFactory.areIntersecting(c1, c2));
		
	}
}
