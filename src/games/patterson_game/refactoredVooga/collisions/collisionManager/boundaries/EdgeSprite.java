package games.patterson_game.refactoredVooga.collisions.collisionManager.boundaries;

import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.CollisionPolygon;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import games.patterson_game.refactoredVooga.util.buildable.components.IComponent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;



@SuppressWarnings("serial")
public class EdgeSprite extends Sprite {

	private int edgeID;

	public EdgeSprite(Point2D p1, Point2D p2, int EdgeID) {
		super(p1.getX(), p1.getY());
		addComponent(new CollisionPolygonC(p1, p2));
		setEdgeID(EdgeID);
	}
	
	public EdgeSprite(double x1, double y1, double x2, double y2, int EdgeID) {
		this(new Point2D.Double(x1,y1),new Point2D.Double(x2,y2), EdgeID);
	}

	public void setEdgeID(int edgeID) {
		this.edgeID = edgeID;
	}

	public int getEdgeID() {
		return edgeID;
	}

}
