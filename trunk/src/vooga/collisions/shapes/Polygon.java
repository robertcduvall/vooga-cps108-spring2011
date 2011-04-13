package vooga.collisions.shapes;

import java.awt.geom.Line2D;

public class Polygon implements Shape
{
	private Vertex[] vertices;
	private Vertex topLeftCorner;
	private Vertex center;
	private double angle;
	private BoundingBox boundingBox;

	public Polygon(Vertex[] verticies)
	{
		this.vertices = verticies.clone();
		this.topLeftCorner = getTopLeftCorner(verticies);
		center = updateCenter();
		boundingBox = new BoundingBox(this.getWidth(), this.getHeight(), this.topLeftCorner);
	}

	@Override
	public BoundingBox getBoundingBox() 
	{
		return boundingBox;
	}
	
	
	
	public void updateBoundingBox()
	{
		boundingBox.x = this.topLeftCorner.getX();
		boundingBox.y = this.topLeftCorner.getY();
		
		boundingBox.width = this.getWidth();
		boundingBox.height = this.getHeight();
	}
	
	private Vertex updateCenter()
	{
		if(center == null)
		{
			center = new Vertex(0,0);
		}
		
		double x = topLeftCorner.getX();
		double y = topLeftCorner.getY();
		
		x = x + this.getWidth()/2;
		y = y + this.getHeight()/2;
		
		center.setX(x);
		center.setY(y);
		return center;
	}
	
	public Vertex getCenter()
	{
		return this.center;
	}

	private double getWidth()
	{
		double topLeftCornerX = Math.abs(topLeftCorner.getX());
		double maxWidth = 0;
		for(Vertex comp : vertices)
		{
			if(comp.getX() > maxWidth)
			{
				maxWidth = Math.abs(comp.getX());
			}
		}
		return Math.abs(maxWidth - topLeftCornerX);
	}
	
	private double getHeight()
	{
		double topLeftCornerY = Math.abs(topLeftCorner.getY());
		double maxHeight = 0;
		for(Vertex comp : vertices)
		{
			if(comp.getY() > maxHeight)
			{
				maxHeight = Math.abs(comp.getY());
			}
		}
		return Math.abs(maxHeight - topLeftCornerY);
	}

	private Vertex getTopLeftCorner(Vertex[] vertices)
	{
		Vertex returnVertex = null;
		
		if(vertices.length > 0)
		{
			double minX = vertices[0].getX();
			double minY = vertices[0].getY();

			for(int i = 1; i < vertices.length; i++)
			{
				if(minX > vertices[i].getX())
				{
					minX = vertices[i].getX();
				}
				
				if(minY > vertices[i].getY())
				{
					minY = vertices[i].getY();
				}
			}
			returnVertex = new Vertex(minX, minY);
		}
		
		return returnVertex;
	}

	private double getRadius(Vertex[] vertices)
	{
		double maxDistance = 0;
		for(Vertex v1 : vertices)
		{
			for(Vertex v2: vertices)
			{
				if(maxDistance < v1.distanceTo(v2))
				{
					maxDistance = v1.distanceTo(v2);
				}
			}
		}
		return maxDistance;
	}

	public Side[] getSides()
	{
		Side[] sideArray = new Side[vertices.length];
		
		for(int i = 0; i < this.vertices.length - 1; i++)
		{
			sideArray[i] = new Side(vertices[i], vertices[i + 1]);
		}
		sideArray[this.vertices.length - 1] = new Side(vertices[this.vertices.length - 1], vertices[0]);
		
		return sideArray;
	}

	public void move(double x, double y)
	{
		for(Vertex comp : vertices)
		{
			comp.setX(comp.getX() + x);
			comp.setY(comp.getY() + y);
		}
		
		this.topLeftCorner.setX(topLeftCorner.getX() + x);
		this.topLeftCorner.setY(topLeftCorner.getY() + y);
		
		this.updateBoundingBox();
		this.updateCenter();
		
		System.out.println("topLeft is " + topLeftCorner.toString());
		System.out.println("center is " + center.toString());
	}
	
	public void setLocation(double x, double y)
	{
		double offsetX = x - topLeftCorner.getX();
		double offsetY = y = topLeftCorner.getY();
		
		this.move(offsetX, offsetY);
	}
	
	public void rotate(double degrees)
	{
		angle += degrees;
		//TODO: finish this
	}

}
