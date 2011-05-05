package games.TimeCrisis.actors;

/**
 * @author Troy Ferrell
 *
 */
public class SpritePath 
{
	double firstPoint_X, firstPoint_Y;
	double secondPoint_X, secondPoint_Y;
	
	double currentPoint_X, currentPoint_Y;
	
	double scaleSize = 0;
	
	public SpritePath(double x1, double y1, double x2, double y2, double scale)
	{
		firstPoint_X = x1;
		firstPoint_Y = y1;
		
		secondPoint_X = x2;
		secondPoint_Y = y2;
		
		scaleSize = scale;
		
		currentPoint_X = firstPoint_X;
		currentPoint_Y = firstPoint_Y;
	}
	
	public void reset()
	{
		currentPoint_X = firstPoint_X;
		currentPoint_Y = firstPoint_Y;
	}
	
	public void move(boolean toSecondPoint, double dTime)
	{
		// Parametric equations
		//(x, y) = (a, b) + tá(u, v),

		double nextPoint_X;
		double nextPoint_Y;
		
		if(toSecondPoint)
		{
			nextPoint_X = currentPoint_X + (secondPoint_X - firstPoint_X)*dTime;
			nextPoint_Y = currentPoint_Y + (secondPoint_Y - firstPoint_Y)*dTime;			
		}
		else
		{
			nextPoint_X = currentPoint_X + (firstPoint_X - secondPoint_X)*dTime;
			nextPoint_Y = currentPoint_Y + (firstPoint_Y - secondPoint_Y)*dTime;
		}
		
		if((nextPoint_X < firstPoint_X && nextPoint_X > secondPoint_X) ||
				(nextPoint_X < secondPoint_X && nextPoint_X > firstPoint_X))
			currentPoint_X = nextPoint_X;
		
		if((nextPoint_Y < firstPoint_Y && nextPoint_Y > secondPoint_Y) ||
				(nextPoint_Y < secondPoint_Y && nextPoint_Y > firstPoint_Y))
			currentPoint_Y = nextPoint_Y;
	}
	
	public double getX()
	{
		return currentPoint_X;
	}
	
	public double getY()
	{
		return currentPoint_Y;
	}
	
	public double getScale()
	{
		return scaleSize;
	}
}
