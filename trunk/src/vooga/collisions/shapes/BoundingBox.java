package vooga.collisions.shapes;
import java.awt.geom.Rectangle2D;


public class BoundingBox extends Rectangle2D.Double
{

	public BoundingBox(double width, double height)
	{
		this.width = width;
		this.height = height;
	}
	
	public BoundingBox(double width, double height, double xPos, double yPos)
	{
		this.x = xPos;
		this.y = yPos;
		this.width = width;
		this.height = height;
	}
	
	public BoundingBox(double width, double height, Vertex vertex)
	{
		this.width = width;
		this.height = height;
		this.x = vertex.getX();
		this.y = vertex.getY();
	}
	
	public BoundingBox(Rectangle2D rectangle)
	{
		this.width = rectangle.getWidth();
		this.height = rectangle.getHeight();
		this.x = rectangle.getMinX() - rectangle.getCenterX();
		this.y = rectangle.getMinY() - rectangle.getCenterY();
	}
}
