package games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class CollisionPolygon extends Polygon implements ICollisionShape 
{

	public CollisionPolygon(ArrayList<double[]> polyCoords, int x, int y) {
		super(polyCoords, x, y);
	}

	public CollisionPolygon(double x, double y, int sideNum, double sideLength) {
		super(x, y, sideNum, sideLength);
	}

	public CollisionPolygon(Vertex... verticies) {
		super(verticies);
	}

	private BoundingBox boundingBox;
	
	public CollisionPolygon(Point2D ... vertices)
	{
		super(vertices);
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}

	public CollisionPolygon(Polygon p) {
		super(p);
	}

	@Override
	public boolean intersects(ICollisionShape s) {
		// TODO unimplemented right now
		return false;
	}
	
}
