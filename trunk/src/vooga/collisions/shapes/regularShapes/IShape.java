package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;


public interface IShape extends java.awt.Shape
{

    public abstract void move (double dx, double dy);


    public abstract void setLocation (double x, double y);


    public abstract void rotate (double degrees);


    public abstract void setAngle (double angle);


    public abstract double getMaxDistanceFromCenter ();


    public abstract Point2D getTopLeftCorner (Point2D[] vertices);


    public abstract Point2D getCenter ();


    public abstract boolean contains (double x, double y);


    public abstract boolean contains (double x1, double y1, double x2, double y2);


    public abstract void setCenter (Point2D center);

}
