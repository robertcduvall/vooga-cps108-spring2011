package vooga.collisions.shapes.collisionShapes;

import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.regularShapes.Polygon;

public class CollisionPolygon extends Polygon implements ICollisionShape 
{

	private BoundingBox boundingBox;
	
	public CollisionPolygon(Point2D ... vertices)
	{
		super(vertices);
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}

	@Override
	public boolean intersects(ICollisionShape s) {
		// TODO unimplemented right now
		return false;
	}
	
}
