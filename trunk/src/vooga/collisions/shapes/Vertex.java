package vooga.collisions.shapes;

public class Vertex implements Cloneable
{
	private double x;
	private double y;
	
	public Vertex(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vertex(Vertex vertex)
	{
		this.x = vertex.x;
		this.y = vertex.y;
	}
	
	public Vertex()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Vertex Clone()
	{
		return new Vertex(this);
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void set(Vertex vertex)
	{
		this.x = vertex.x;
		this.y = vertex.y;
	}
	
	public double distanceTo(Vertex vertex)
	{
		double dx = vertex.getX() - this.getX();
		double dy = vertex.getY() - this.getY();
		
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	public double diagonalLengthFromOrigin()
	{
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	public String toString()
	{
		return "X = " + this.getX() + " Y = " +this.getY();
	}
	
}
