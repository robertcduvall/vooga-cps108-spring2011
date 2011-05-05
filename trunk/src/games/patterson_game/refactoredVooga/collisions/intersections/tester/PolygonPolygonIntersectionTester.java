package games.patterson_game.refactoredVooga.collisions.intersections.tester;

import games.patterson_game.refactoredVooga.collisions.intersections.IntersectionFactory;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Circle;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Polygon;
import java.awt.geom.Point2D;


public class PolygonPolygonIntersectionTester 
{
	public static void main(String[] args)
	{
		//first make the shapes (START THEM WITH BLANK CONSTUCTORS)
		Point2D[] points = new Point2D[]{
				new Point2D.Double(10,10),
				new Point2D.Double(20,10),
				new Point2D.Double(20,20),
				new Point2D.Double(10,20),
				};
		
		Polygon p1= new Polygon(points);
		
		Point2D[] points2 = new Point2D[]{
				new Point2D.Double(75,75),
				new Point2D.Double(85,75),
				new Point2D.Double(85,85),
				new Point2D.Double(75,85),
				};
		Polygon p2 = new Polygon(points2);


		//IntersectionFactory intFactory = new IntersectionFactory();
		System.out.println("Circles are not intersecting: (should return true) " + !IntersectionFactory.areIntersecting(p1, p2));
		
		points2 = new Point2D[]{
				new Point2D.Double(15,15),
				new Point2D.Double(25,15),
				new Point2D.Double(25,25),
				new Point2D.Double(15,25),
				};
		
		p2 = new Polygon(points2);
		
		System.out.println("Circles are intersecting: (should return true) " + IntersectionFactory.areIntersecting(p1, p2));

		p1.setCenter(new Point2D.Double(50,50));
		System.out.println("Circles are intersecting: (should return true) " + IntersectionFactory.areIntersecting(p1, p2));

	}
}
