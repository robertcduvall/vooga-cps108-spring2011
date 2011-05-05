package games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Circle;
import java.awt.Shape;
import java.awt.geom.Point2D;


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
