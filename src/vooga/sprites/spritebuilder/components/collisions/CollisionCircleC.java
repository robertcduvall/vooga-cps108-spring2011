package vooga.sprites.spritebuilder.components.collisions;

import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.CollisionCircle;
import vooga.collisions.shapes.collisionShapes.ICollisionShape;

public class CollisionCircleC extends CollisionShapeC {

	
	public CollisionCircleC (Point2D center, double radius)
    {
		this(new CollisionCircle(center, radius));
    }
	
	public CollisionCircleC(CollisionCircle cs) {
		super(cs);
	}

}
