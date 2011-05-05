package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;

import vooga.levels.IRenderable;
import vooga.sprites.improvedsprites.interfaces.IRotation;


public interface IShape extends IRotation, IRenderable
{

    public abstract void move (double dx, double dy);

/**
 * based on upper left corner
 * @param x
 * @param y
 */
    public abstract void setLocation (double x, double y);


    public abstract double getMaxDistanceFromCenter ();


    public abstract Point2D getTopLeftCorner ();


    public abstract Point2D getCenter ();


    public abstract boolean contains (double x, double y);


    public abstract boolean contains (double x1, double y1, double x2, double y2);


    public abstract void setCenter (Point2D.Double center);
    
    public abstract void setCenter (double x, double y);

	double getWidth();

	double getHeight();

}
