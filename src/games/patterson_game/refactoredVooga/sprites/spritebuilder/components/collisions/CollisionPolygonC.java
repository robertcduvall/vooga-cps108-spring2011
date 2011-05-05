package games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.BoundingBox;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.CollisionPolygon;
import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.ICollisionShape;
import java.awt.geom.Point2D;


public class CollisionPolygonC extends CollisionShapeC<CollisionPolygon> {

	
	
	public CollisionPolygonC(Point2D ... vertices)
	{
		super(new CollisionPolygon(vertices));
	}
	
	public CollisionPolygonC(CollisionPolygon cs) {
		super(cs);
	}


}
