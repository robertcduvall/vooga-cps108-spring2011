package vooga.debugger.view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JFrame;



/**
 * @author Troy Ferrell & Austin Benesh
 *
 */
public class DebuggerGrapher extends JFrame
{
	// private JPanel myCanvas;
	
	private vooga.debugger.model.GameField myField;
	
	private ArrayList<Double> myFieldValues;
	private ArrayList<Double> myTimeValues;
	
	public DebuggerGrapher()
	{
		myFieldValues = new ArrayList<Double>();
		myTimeValues = new ArrayList<Double>();
	}
	
	public void clear()
	{
		myFieldValues.clear();
		myTimeValues.clear();
	}
	
	public void udpate(long deltaTime)
	{
		myTimeValues.add(myTimeValues.get(myTimeValues.size() - 1) + deltaTime );
		
		// myFieldValues.add(Double.valueOf(myField));
		
		super.repaint();
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
		
		// g2d.scale(sx, sy);
		//g2d.translate(0.0, this.getSize().height);
		
		drawGraph(g2d);
		drawData(g2d);
	}
	
	private void drawData(Graphics2D g2d)
	{
		Point firstPoint = new Point();
		for(int i = 1; i < myFieldValues.size(); i++)
		{
			Point lastPoint = new Point( myTimeValues.get(i).intValue(), myFieldValues.get(i).intValue());
			
			g2d.drawLine(firstPoint.x, firstPoint.y, lastPoint.x, lastPoint.y);
			
			firstPoint = lastPoint;
		}
	}
	
	private void drawGraph(Graphics2D g2d)
	{
		g2d.drawLine(0, 0, 0, Collections.max(myFieldValues).intValue());
		g2d.drawLine(0, 0, Collections.max(myTimeValues).intValue(), 0);
		
		// need to scale max value as max value of pixels
		
	}
}
