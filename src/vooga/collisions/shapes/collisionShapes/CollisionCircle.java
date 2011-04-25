package vooga.collisions.shapes.collisionShapes;

import java.awt.Shape;
import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.regularShapes.Circle;

public class CollisionCircle extends Circle implements Shape, ICollisionShape
{

	public CollisionCircle() {
		super();
	}

	public CollisionCircle(Circle c) {
		super(c);
	}

	public CollisionCircle(double x, double y, double r) {
		super(x, y, r);
	}

	public CollisionCircle(Point2D center, double radius) {
		super(center, radius);
	}

	public CollisionCircle(Vertex center, double radius) {
		super(center, radius);
	}

	@Override
	public boolean intersects(ICollisionShape s) {
		
		//TODO set intersectfinder method
		
		
		return false;
	}
}
