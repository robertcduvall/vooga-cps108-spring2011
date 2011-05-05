package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.CollisionCircle;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.ICollisionShape;
import java.awt.geom.Point2D;


public class CollisionCircleC extends CollisionShapeC<CollisionCircle> {

	
	public CollisionCircleC (Point2D center, double radius)
    {
		this(new CollisionCircle(center, radius));
    }
	
	public CollisionCircleC(CollisionCircle cs) {
		super(cs);
	}

}
