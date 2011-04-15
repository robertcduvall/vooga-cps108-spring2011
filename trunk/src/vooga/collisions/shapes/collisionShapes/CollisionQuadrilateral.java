package vooga.collisions.shapes.collisionShapes;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.regularShapes.Quadrilateral;

public class CollisionQuadrilateral extends Quadrilateral implements ICollisionShape
{

	public CollisionQuadrilateral(Vertex v1, Vertex v2, Vertex v3, Vertex v4) {
		super(v1, v2, v3, v4);
	}

	@Override
	public boolean intersects(ICollisionShape s) {
		// TODO unimplemented
		return false;
	}

}
