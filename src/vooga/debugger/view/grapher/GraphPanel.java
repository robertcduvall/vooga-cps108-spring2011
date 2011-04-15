
package vooga.debugger.view.grapher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * @author Troy Ferrell
 *
 */
public class GraphPanel extends JPanel 
{
	private double scaleRatio = 0.9;
	
	public GraphPanel()
	{

		this.setBackground(Color.white);
	}
	
	public void zoomIn()
	{
		
	}
	
	public void zoomOut()
	{
		
	}

	/**
	 * Method in charge of drawing scene onto JPanel(aka canvas)
	 * 
	 * @param g - Graphics object passed by java system to help with drawing
	 */
	public void paint(Graphics g)
	{
		// draw graph here
		Graphics2D g2d = (Graphics2D)g;
	
		//pixelHeight = this.getHeight()*scaleRatio;
		//pixelWidth = this.getWidth()*scaleRatio;
		
		// get affine transform here
		
		// g2d.scale(sx, sy);
		g2d.translate(this.getWidth()*(1 - scaleRatio), this.getHeight()*scaleRatio);
		
		//drawGraph(g2d);
		//drawData(g2d);
		
		// reset affine transform here
	}

	/*
	 * to transform data range to pixel range
	 * 
	 # Figure out how 'wide' each range is
	    leftSpan = leftMax - leftMin
	    rightSpan = rightMax - rightMin

	    # Convert the left range into a 0-1 range (float)
	    valueScaled = float(value - leftMin) / float(leftSpan)

	    # Convert the 0-1 range into a value in the right range.
	    return rightMin + (valueScaled * rightSpan)
*/
	
	public Point transformCoord(Point p)
	{
		return null;
	}
	
	/*
	private void drawData(Graphics2D g2d)
	{
		Point firstPoint = new Point();
		for(int i = 1; i < myFieldValues.size(); i++)
		{
			// TODO: need to normalize coordinates based on max****
			
			Point lastPoint = new Point( myTimeValues.get(i).intValue(), -myFieldValues.get(i).intValue());
			
			g2d.drawLine(firstPoint.x, firstPoint.y, lastPoint.x, lastPoint.y);
			
			firstPoint = lastPoint;
		}
	}
	
	private void drawGraph(Graphics2D g2d)
	{
		//g2d.drawLine(0, 0, 0, -Collections.max(myFieldValues).intValue());
		//g2d.drawLine(0, 0, Collections.max(myTimeValues).intValue(), 0);
	}*/
	
}
