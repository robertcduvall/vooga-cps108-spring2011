
package vooga.debugger.view.grapher;

/**
 * Similar to Point class in Java api but uses double instead of integers
 * 
 * @author Troy Ferrell
 */
public class DataPoint 
{
	private double x, y;
	
	public DataPoint()
	{
		this(0.0, 0.0);
	}
	
	public DataPoint(double newX, double newY)
	{
		x = newX;
		y = newY;
	}
	
	public double getX() 
	{ return x; }
	
	public double getY()
	{ return y; }
	
	public void setX(double newX)
	{ x = newX; }
	
	public void setY(double newY)
	{ y = newY; }
}

