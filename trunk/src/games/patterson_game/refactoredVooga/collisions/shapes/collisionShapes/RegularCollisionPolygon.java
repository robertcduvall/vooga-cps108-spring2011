package games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.RegularPolygon;
import games.patterson_game.refactoredVooga.collisions.shapes.util.PolygonMath;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;



public class RegularCollisionPolygon extends CollisionPolygon {

	

	@Override
	public boolean intersects(ICollisionShape s) {
		
		return false;
	}


}
