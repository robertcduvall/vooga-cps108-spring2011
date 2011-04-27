package vooga.collisions.collisionManager.boundaries;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import vooga.collisions.shapes.collisionShapes.CollisionPolygon;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.collisions.CollisionPolygonC;
import vooga.util.buildable.components.IComponent;


@SuppressWarnings("serial")
public class EdgeSprite extends Sprite {


	public EdgeSprite(Point2D p1, Point2D p2) {
		super(p1.getX(), p1.getY());
		addComponent(new CollisionPolygonC(p1, p2));
	}
	
	public EdgeSprite(double x1, double y1, double x2, double y2) {
		this(new Point2D.Double(x1,y1),new Point2D.Double(x2,y2));
	}

}
