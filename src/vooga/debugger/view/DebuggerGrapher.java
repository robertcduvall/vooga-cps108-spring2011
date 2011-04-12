package vooga.debugger.view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JFrame;

import Debugger.model.GameField;



/**
 * WIP class - going to be used for recording values over time in game and displaying graphically
 * 
 * @author Troy Ferrell
 */
public class DebuggerGrapher extends JFrame
{
	// private JPanel myCanvas;
	
	private GameField myField;
	
	private ArrayList<Double> myFieldValues;
	private ArrayList<Double> myTimeValues;
	
	public DebuggerGrapher()
	{
		myFieldValues = new ArrayList<Double>();
		myFieldValues.add(0.0);
		
		
		myFieldValues.add(150.0);
		myFieldValues.add(250.0);
		myFieldValues.add(350.0);
		
		myTimeValues = new ArrayList<Double>();
		myTimeValues.add(0.0);
		myTimeValues.add(100.0);
		myTimeValues.add(200.0);
		myTimeValues.add(300.0);
	}
	
	public void clear()
	{
		myFieldValues.clear();
		myTimeValues.clear();
		
		myFieldValues.add(0.0);
		myTimeValues.add(0.0);
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
		g2d.translate(100, this.getSize().height - 100);
		
		drawGraph(g2d);
		drawData(g2d);
	}
	
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
	}
	
	public static void main(String [] args)
	{
		DebuggerGrapher grapher = new DebuggerGrapher();
		grapher.setVisible(true);
		
		
	}
}
