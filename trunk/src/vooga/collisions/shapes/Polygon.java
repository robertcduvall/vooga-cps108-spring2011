package vooga.collisions.shapes;

import java.awt.geom.Line2D;

public class Polygon implements IBoxBounded
{
	private Vertex[] vertices;
	private Vertex topLeftCorner;
	private Vertex myCenter;
	private double angle;
	private BoundingBox boundingBox;

	public Polygon(Vertex ... verticies)
	{
		this.vertices = verticies.clone();
		this.topLeftCorner = getTopLeftCorner(verticies);
		myCenter = updateCenter();
		boundingBox = new BoundingBox(this.topLeftCorner, this.getWidth(), this.getHeight());
	}

	@Override
	public BoundingBox getBoundingBox() 
	{
		return boundingBox;
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
	
	public Vertex getCenter()
	{
		return this.myCenter;
	}

	private double getWidth()
	{
//		double topLeftCornerX = Math.abs(topLeftCorner.getX());
//		double maxWidth = 0;
//		for(Vertex comp : vertices)
//		{
//			if(comp.getX() > maxWidth)
//			{
//				maxWidth = Math.abs(comp.getX());
//			}
//		}
		return PolygonMath.getMaxX(this) - topLeftCorner.getX();
	}
	
	private double getHeight()
	{
//		double topLeftCornerY = Math.abs(topLeftCorner.getY());
//		double maxHeight = 0;
//		for(Vertex comp : vertices)
//		{
//			if(comp.getY() > maxHeight)
//			{
//				maxHeight = Math.abs(comp.getY());
//			}
//		}
		return PolygonMath.getMaxY(this) - topLeftCorner.getY();
	}

	private Vertex getTopLeftCorner(Vertex[] vertices)
	{
//		Vertex returnVertex = null;
//		
//		if(vertices.length > 0)
//		{
//			double minX = vertices[0].getX();
//			double minY = vertices[0].getY();
//
//			for(int i = 1; i < vertices.length; i++)
//			{
//				if(minX > vertices[i].getX())
//				{
//					minX = vertices[i].getX();
//				}
//				
//				if(minY > vertices[i].getY())
//				{
//					minY = vertices[i].getY();
//				}
//			}
//			returnVertex = new Vertex(minX, minY);
//		}
//		
		return new Vertex(PolygonMath.getMinX(vertices), PolygonMath.getMinY(vertices));
	}

	private double getRadius(Vertex[] vertices)
	{
		double maxDistance = 0;
		for(Vertex v1 : vertices)
		{
			if (v1.distance(myCenter) > maxDistance)
			    maxDistance = v1.distance(myCenter);
		}
		return maxDistance;
	}

	public Line2D[] getSides()
	{
		Side[] sideArray = new Side[vertices.length];
		
		for(int i = 0; i < this.vertices.length; i++)
			sideArray[i] = new Side(vertices[i], vertices[(i + 1)%vertices.length]);
		
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

    public Vertex[] getVertices ()
    {
        return vertices;
    }

}
