package vooga.collisions.shapes.collisionShapes;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.regularShapes.RegularPolygon;
import vooga.collisions.shapes.util.PolygonMath;


public class RegularCollisionPolygon extends CollisionPolygon {

	

	@Override
	public boolean intersects(ICollisionShape s) {
		
		return false;
	}


}
