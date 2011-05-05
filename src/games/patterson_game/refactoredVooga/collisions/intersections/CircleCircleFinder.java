package games.patterson_game.refactoredVooga.collisions.intersections;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.CollisionCircle;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Circle;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.IShape;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Polygon;
import games.patterson_game.refactoredVooga.util.math.LineMath;

public class CircleCircleFinder extends IntersectionFinder<Circle, Circle> {

	public CircleCircleFinder()
	{
		c1 = Circle.class;
		c2 = Circle.class;
	}

    @Override
    public Intersection getIntersection (Circle c1, Circle c2)
    {
        double dist = c1.getCenter().distance(c2.getCenter());
        double dir = LineMath.findDirection(c1.getCenter().getX(), c1.getCenter().getY(), c2.getCenter().getX(), c1.getCenter().getY());
        
        double a = (Math.pow(c1.getRadius(), 2)-Math.pow(c2.getRadius(), 2) + Math.pow(dist, 2))/(2*dist);
        double h = Math.pow(c1.getRadius(), 2) - Math.pow(a,2);
        
        Vertex int1 = new Vertex(c1.getCenter());
        Vertex int2 = new Vertex(c1.getCenter());
        int1.move(a*Math.cos(Math.toRadians(dir)),a*Math.sin(Math.toRadians(dir)));
        int1.move(h*Math.cos(Math.toRadians(dir+90)),h*Math.sin(Math.toRadians(dir+90)));
        
        int2.move(a*Math.cos(Math.toRadians(dir)),a*Math.sin(Math.toRadians(dir)));
        int2.move(h*Math.cos(Math.toRadians(dir-90)),h*Math.sin(Math.toRadians(dir-90)));
        
        return new Intersection(int1, int2);
        
        //a = (r02 - r12 + d2 ) / (2 d)
        // h2 = r02 - a2
//        x3 = x2 +- h ( y1 - y0 ) / d
//        y3 = y2 -+ h ( x1 - x0 ) / d
    }

    /**
     * Returns true if the sum of the radii is greater than the distance between the center of the circles
     */
    @Override
    public boolean areIntersecting (Circle c1, Circle c2)
    {
        return (c1.getRadius() + c2.getRadius()) > c1.getCenter().distance(c2.getCenter());
    }

	@Override
	public int compareTo(Object o) {
		return this.hashCode() - o.hashCode();
	}


}
