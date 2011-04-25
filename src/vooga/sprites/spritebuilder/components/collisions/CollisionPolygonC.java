package vooga.sprites.spritebuilder.components.collisions;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.collisionShapes.ICollisionShape;

public class CollisionPolygonC extends CollisionShapeC<CollisionPolygon> {

	
	
	public CollisionPolygonC(Vertex ... vertices)
	{
		super(new CollisionPolygon(vertices));
	}
	
	public CollisionPolygonC(CollisionPolygon cs) {
		super(cs);
	}

}
