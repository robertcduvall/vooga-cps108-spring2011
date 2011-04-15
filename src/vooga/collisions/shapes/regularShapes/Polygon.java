package vooga.collisions.shapes.regularShapes;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.util.PolygonMath;

public class Polygon extends Shape
{
	protected Vertex[] vertices;
	protected Vertex topLeftCorner;
	protected Vertex myCenter;
	protected double angle;
	protected BoundingBox boundingBox;

	public Polygon(Vertex ... verticies)
	{
		this.vertices = verticies.clone();
		this.topLeftCorner = new Vertex(getTopLeftCorner(this.vertices));
		myCenter = updateCenter();
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}
	
	public Polygon(Point2D ... vertices)
	{
		this.vertices = new Vertex[vertices.length];
		for(int i = 0; i < vertices.length ; i++)
		{
			this.vertices[i] = new Vertex(vertices[i]);
		}
		this.topLeftCorner = new Vertex(getTopLeftCorner(this.vertices));
		myCenter = updateCenter();
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}

	public void updateBoundingBox()
	{
	    boundingBox.setFrame(topLeftCorner.getX(),topLeftCorner.getY(),this.getWidth(), this.getHeight());
	}
	
	private Vertex updateCenter()
	{
		if(myCenter == null)
		{
			myCenter = new Vertex(0,0);
		}
		
		myCenter.setLocation(topLeftCorner.getX() + this.getWidth()/2, topLeftCorner.getY() + this.getHeight()/2);
		return myCenter;
	}

	protected double getWidth()
	{
		return PolygonMath.getMaxX(this) - topLeftCorner.getX();
	}
	
	protected double getHeight()
	{
		return PolygonMath.getMaxY(this) - topLeftCorner.getY();
	}

	public Point2D getTopLeftCorner(Point2D[] vertices)
	{

		return new Point2D.Double(PolygonMath.getMinX(vertices), PolygonMath.getMinY(vertices));
	}

	public double getMaxDistanceFromCenter()
	{
		double maxDistance = 0;
		for(Vertex v1 : this.vertices)
		{
			if (v1.distance(myCenter) > maxDistance)
			    maxDistance = v1.distance(myCenter);
		}
		return maxDistance;
	}

	public Line2D[] getSides()
	{
		Line2D.Double[] sideArray = new Line2D.Double[vertices.length];
		
		for(int i = 0; i < this.vertices.length; i++)
			sideArray[i] = new Line2D.Double((Point2D)vertices[i], (Point2D)vertices[(i + 1)%vertices.length]);
		
		return sideArray;
	}

	public void move(double dx, double dy)
	{
		for(Vertex comp : vertices)
			comp.setLocation(comp.getX() + dx, comp.getY() + dy);
		
		this.topLeftCorner.setLocation(topLeftCorner.getX() + dx, topLeftCorner.getY() + dy);
		
		this.updateBoundingBox();
		this.myCenter.setLocation(myCenter.getX() + dx, myCenter.getY() + dy);
		
		System.out.println("topLeft is " + topLeftCorner.toString());
		System.out.println("center is " + myCenter.toString());
	}
	
	public void setLocation(double x, double y)
	{
		double offsetX = x - topLeftCorner.getX();
		double offsetY = y - topLeftCorner.getY();
		
		this.move(offsetX, offsetY);
	}
	
	public void rotate(double degrees)
	{
		angle += degrees;
		//TODO: finish this
	}
	
	@Override
	public void setAngle(double angle) 
	{
		// TODO Auto-generated method stub
	}
    
    public Point2D[] getPoints()
    {
    	return vertices;
    }

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}





}
