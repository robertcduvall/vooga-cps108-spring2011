package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.util.PolygonMath;
import vooga.util.math.LineMath;

public class Polygon extends Shape
{
	protected Vertex[] vertices;
	protected Vertex topLeftCorner;
	protected double angle;
	protected BoundingBox boundingBox;

	public Polygon(Vertex ... verticies)
	{
		this.vertices = verticies.clone();
		this.topLeftCorner = new Vertex(getTopLeftCorner(this.vertices));
		center = updateCenter();
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}

	public Polygon(Point2D ... vertices)
	{
		this.setVertices(vertices);
	}

	public Polygon(ArrayList<double[]> polyCoords, int x, int y) 
	{
		java.util.Iterator<double[]> iter = polyCoords.iterator();
		Point2D.Double[] vertices = new Point2D.Double[polyCoords.size()];

		int i = 0;
		while(iter.hasNext())
		{
			double[] comp = iter.next();
			Point2D.Double addPoint = new Point2D.Double(comp[0] * x, comp[1] * y);

			vertices[i] = addPoint;
			i++;
		}
		this.setVertices(vertices);
	}

	private void setVertices(Point2D ... vertices)
	{
		this.vertices = new Vertex[vertices.length];

		for(int i = 0; i < vertices.length ; i++)
		{
			this.vertices[i] = new Vertex(vertices[i]);
		}

		this.topLeftCorner = new Vertex(getTopLeftCorner(this.vertices));
		center = updateCenter();
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}

	public void updateBoundingBox()
	{
		boundingBox.setFrame(topLeftCorner.getX(),topLeftCorner.getY(),this.getWidth(), this.getHeight());
	}

	private Vertex updateCenter()
	{
		if(center == null)
		{
			center = new Vertex(0,0);
		}

		center.setLocation(topLeftCorner.getX() + this.getWidth()/2, topLeftCorner.getY() + this.getHeight()/2);
		return center;
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
			if (v1.distance(center) > maxDistance)
				maxDistance = v1.distance(center);
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
		for(Vertex comp : this.vertices)
			comp.setLocation(comp.getX() + dx, comp.getY() + dy);

		this.topLeftCorner.setLocation(topLeftCorner.getX() + dx, topLeftCorner.getY() + dy);

		this.updateBoundingBox();
		this.center.setLocation(center.getX() + dx, center.getY() + dy);

		//System.out.println("topLeft is " + topLeftCorner.toString());
		//System.out.println("center is " + center.toString());
	}

	public void setLocation(double x, double y)
	{
		double offsetX = x - topLeftCorner.getX();
		double offsetY = y - topLeftCorner.getY();

		this.move(offsetX, offsetY);
	}

	/*
	public void rotate(double degrees)	//I think this is right. I haven't tested it.
	{
		//this.move(60,60);
		for(Vertex vertex : this.vertices)
		{
		    vertex.rotate(point, angle)
			//lets get the distance to move x first
			//double hypotenuse = LineMath.length(new Line2D.Double(vertex, this.center));
			//System.out.println(this.center);
			double dx = Math.sin(Math.toRadians(degrees)) * LineMath.length(new Line2D.Double(vertex, this.center));

			//now y
			//double angleA = (90 - ((180 - degrees) / 2));
			double dy = Math.tan(Math.toRadians( (90 - ((180 - degrees) / 2)))) * dx;

			vertex.setLocation(vertex.getX() + dx, vertex.getY() + dy);
			System.out.println("dx = " + dx);
		}
		this.angle += degrees;
	}
	 */

	@Override
	public java.lang.Double setAngle(double angle) 
	{
		return this.rotate(angle - this.angle);
	}

	public Point2D[] getPoints()
	{
		return vertices;
	}


	@Override
	public boolean contains(double x, double y) {
		// TODO figure out how a polygon contains a point
		return false;
	}

	@Override
	public Double getAngle() {
		//TODO how do you get the "angle" of the shape?
		return 0.0;
	}

	@Override
	public Double rotate(double dAngle) {
		
		for (Vertex v: this.vertices)
			v.rotateAround(this.getCenter(), dAngle);
		
		return 0.0;
	}

	



}
