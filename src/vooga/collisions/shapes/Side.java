package vooga.collisions.shapes;
import java.awt.geom.Line2D;


public class Side 
{
	private Line2D.Double line;
	public Vertex v1;
	public Vertex v2;
	
	public Side(Vertex v1, Vertex v2)
	{
		this.v1 = v1;
		this.v2 = v2;
		
		line = new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY());
	}
	
	public boolean intersects(Vertex v1, Vertex v2)
	{
		Line2D.Double intersectLine = new Line2D.Double(v1.getX(), v1.getY(), v2.getX(), v2.getY());
		return intersectLine.intersectsLine(this.line);
	}
	
	public boolean intersects(Line2D.Double otherLine)
	{
		return this.line.intersectsLine(otherLine);
	}
	
	public boolean intersects(Side otherSide)
	{
		return this.line.intersectsLine(otherSide.line);
	}
	
}
