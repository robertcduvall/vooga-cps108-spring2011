package vooga.sprites.spritebuilder.components.collisions;

import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import vooga.collisions.shapes.regularShapes.Polygon;

public class CollisionPolygonC extends CollisionShapeC<CollisionPolygon> {

	
	
	public CollisionPolygonC(Point2D ... vertices)
	{
		super(new CollisionPolygon(vertices));
	}
	
	public CollisionPolygonC(CollisionPolygon cs) {
		super(cs);
	}

	public CollisionPolygonC(Polygon p){
		this(new CollisionPolygon(p));
	}

}
