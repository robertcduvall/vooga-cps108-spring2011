package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;

public abstract class Shape implements java.awt.Shape
{
	protected Vertex center;

	public abstract void move(double dx, double dy);
	
	public abstract void setLocation(double x, double y);
	
	public abstract void rotate(double degrees);
	
	public abstract void setAngle(double angle);
	
	public abstract double getMaxDistanceFromCenter();
	
	public abstract Point2D getTopLeftCorner(Point2D[] vertices);
	
	public Point2D getCenter()
	{
		return center;
	}
	
	public boolean contains (double x, double y)
    {
        return this.contains(new Point2D.Double(x, y));
    }
	
    public boolean contains (double x1, double y1, double x2, double y2)
    {
        return this.contains(x1, y1) && this.contains(x2, y2);
    }
    
    public void setCenter (Point2D center)
    {
        this.center.setLocation(center);
    }

}
