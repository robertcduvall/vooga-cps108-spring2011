package vooga.collisions.shapes;

import java.awt.geom.Point2D;

public class Vertex extends Point2D.Double implements Cloneable
{
	
	
	@Override
    public Vertex clone ()
    {
        return new Vertex(this);
    }

    public Vertex ()
    {
        super();
    }

    public Vertex (double x, double y)
    {
        super(x, y);
    }
    
    public Vertex (Point2D p){
        super(p.getX(), p.getY());
    }
    
    public void move(double dx, double dy)
    {
    	this.x += dx;
    	this.y += dy;
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
