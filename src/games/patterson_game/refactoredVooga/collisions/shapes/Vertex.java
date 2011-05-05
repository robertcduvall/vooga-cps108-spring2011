package games.patterson_game.refactoredVooga.collisions.shapes;

import games.patterson_game.refactoredVooga.util.math.LineMath;
import java.awt.geom.Line2D;
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
    	//System.out.println("was: " + this.x);
    	this.setLocation(this.x + dx, this.y + dy);
    	//System.out.println("now: " + this.x);
    }
    
    public double diagonalLengthFromOrigin()
	{
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
	
	public String toString()
	{
		return "X = " + this.getX() + " Y = " +this.getY();
	}
	
	public Point2D rotateAround(Point2D center, double dAngle) {
		this.setLocation(rotateAround(this, center, dAngle));
		return this;
				
	}

	public static Point2D rotateAround(Point2D p, Point2D center, double dAngle) {
		return LineMath.rotate(new Line2D.Double(center,p), dAngle).getP2();
				
	}

}
