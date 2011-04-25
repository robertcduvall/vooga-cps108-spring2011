
package vooga.debugger.view.grapher;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import vooga.debugger.util.MethodAction;
import vooga.debugger.view.GameTreeNode;



/**
 * Canvas class in charge of drawing plot from GraphGameField's recorded data
 * 
 * @author Troy Ferrell
 */
public class GrapherPanel extends JPanel implements MouseListener, MouseMotionListener
{	
	private double pointScaleRatio_X, pointScaleRatio_Y;
	private double translateX, translateY;
	
	private Point clickPoint;
	private Point cursorPosition = new Point();
	
	private GraphToolset myToolSet;
	private DebuggerGrapher myDebugGrapher;
	
	private JPopupMenu myCanvasPopup;
	
	public GrapherPanel(GraphToolset toolset, DebuggerGrapher debugGrapher)
	{
		myToolSet = toolset;
		myDebugGrapher = debugGrapher;
		
		resetCanvas();
		initPopup();
		
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	/**
	 * Initi Popup menu for grapher canvas 
	 */
	private void initPopup()
	{
		myCanvasPopup = new JPopupMenu();
		JMenuItem resetCanvasItem = new JMenuItem("Reset Canvas");
		resetCanvasItem.addActionListener( new MethodAction(this, "resetCanvas"));
		myCanvasPopup.add( resetCanvasItem );
	}
	
	/**
	 * Reset canvas to initial viewport
	 */
	public void resetCanvas()
	{
		pointScaleRatio_X = 1;
		pointScaleRatio_Y = 1;
		
		translateX = 0.1;
		translateY = 0.9;
		
		this.repaint();
	}
	
	/**
	 * Zoom in on canvas scene
	 * 
	 * @param zoomX - enable zooming for x-axis
	 * @param zoomY - enable zooming for y-axis
	 */
	public void zoomIn(boolean zoomX, boolean zoomY)
	{
		// TODO: Need to turn hardcoded values into variables possibly settings from xml or resource file
		
		if(pointScaleRatio_X*1.5 < 8 && zoomX)
			pointScaleRatio_X *= 1.5;
		
		if(pointScaleRatio_Y*1.5 < 8 && zoomY)
			pointScaleRatio_Y *= 1.5;
	}
	
	/**
	 * Zoomout on canvas scene
	 * 
	 * @param zoomX - enable zooming for x-axis
	 * @param zoomY - enable zooming for y-axis
	 */
	public void zoomOut(boolean zoomX, boolean zoomY)
	{
		if(pointScaleRatio_X/1.5 > 1/8.0 && zoomX)
			pointScaleRatio_X /= 1.5;
		
		if(pointScaleRatio_Y/1.5 > 1/8.0 && zoomY)
			pointScaleRatio_Y /= 1.5;
	}

	/**
	 * Method in charge of drawing scene onto JPanel(aka canvas)
	 * 
	 * @param g - Graphics object passed by java system to help with drawing
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		drawGraph(g2d);
		
		AffineTransform at = g2d.getTransform();
		
			g2d.translate( this.getWidth()*translateX,  this.getHeight()*translateY);
	
			drawData(g2d);
			
		g2d.setTransform(at);
	}

	/**
	 * Takes parameter datapoint and transforms coordinates so they are
	 * appropriately scaled when drawn to the canvas
	 *  
	 * @param p - point to transform
	 * @param maxX - max x value from data collection of the field
	 * @param maxY - max y value from data collection of the field
	 * @return
	 */
	public DataPoint transformCoord(DataPoint p, double maxX, double maxY)
	{
		double newX = p.getX() * (this.getWidth() * pointScaleRatio_X / maxX);
		
		double newY = - p.getY() * (this.getHeight() * pointScaleRatio_Y / maxY);
		
		return new DataPoint(newX, newY);
	}
	
	/**
	 * Draw all of the GraphGameField plots to scene
	 * @param g2d
	 */
	private void drawData(Graphics2D g2d)
	{
		for(GraphGameField gf : myDebugGrapher.myGraphFields)
		{
			if(gf.isDrawable())
			{
				ArrayList<DataPoint> points = new ArrayList<DataPoint>(gf.getData());
				DataPoint maxPoint = gf.getMaxPoint();
				
				DataPoint firstPoint = transformCoord( points.get(0), maxPoint.getX(), maxPoint.getY());
				for(int i = 1; i < points.size(); i++)
				{
					DataPoint lastPoint = transformCoord(points.get(i), maxPoint.getX(), maxPoint.getY());
					
					g2d.setColor(gf.getPlotColor());
					
					g2d.drawLine((int)firstPoint.getX(), (int)firstPoint.getY(), (int)lastPoint.getX(), (int)lastPoint.getY());
					
					// TODO: should probably store size of graph point radius somewhere
					
					g2d.fillOval((int)firstPoint.getX() - 2, (int)firstPoint.getY() - 2, 5, 5);
					
					firstPoint = lastPoint;
				}
				
				// Draw the last point graph dot
				g2d.fillOval((int)firstPoint.getX() - 2, (int)firstPoint.getY() - 2, 5, 5);
				
				// Draw Closest Point to Cursor
				DataPoint cursorPoint = getClosestPointToCursor(points, maxPoint);
				if(cursorPoint != null)
				{
					gf.updateSelectPoint(cursorPoint);
					
					g2d.setColor(Color.RED);
					DataPoint cursorPoint_Transformed = transformCoord(cursorPoint, maxPoint.getX(), maxPoint.getY());
					g2d.fillOval((int)cursorPoint_Transformed.getX() - 5, (int)cursorPoint_Transformed.getY() - 5, 10, 10);
				}
			}
		}
	}
	
	/**
	 * Finds closest point to cursor position by taking cursors' x position 
	 * and reverse translating from the canvas domain to the data collections domain. 
	 * Then seraches for the nearest point in the data collection along with respect to the x-axis 
	 * 
	 * @param data - collection of datapoints to search
	 * @param max - max x & y values of collections to reverse domain the cursor
	 * @return
	 */
	private DataPoint getClosestPointToCursor(ArrayList<DataPoint> data, DataPoint max)
	{
		// transform cursor_X from pixel coordinates to data coordinates
		double cursorX_Transformed = (cursorPosition.getX() - this.getWidth()*this.translateX)*(max.getX()/(this.pointScaleRatio_X*this.getWidth()));
		
		DataPoint closestPoint = null;
		double smallestDifference = Double.MAX_VALUE;
		
		for(DataPoint dp : data)
		{
			double pointDifference = Math.abs(dp.getX() - cursorX_Transformed);
			if(pointDifference < smallestDifference)
			{
				smallestDifference = pointDifference;
				closestPoint = dp;
			}
		}

		return closestPoint;
	}
	
	/**
	 * Draw graph outlines
	 * @param g2d
	 */
	private void drawGraph(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g2d.setColor(Color.black);
		
		// draw y-axis
		g2d.drawLine((int)(this.getWidth()*translateX), 0, (int)(this.getWidth()*translateX), this.getHeight());
		
		// draw x-axis
		g2d.drawLine(0, (int)(this.getHeight()*translateY), this.getWidth(), (int)(this.getHeight()*translateY));
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{		
		// If right click, show popup window
		if(e.getButton() == e.BUTTON3)
   	 	{
			myCanvasPopup.show(e.getComponent(), e.getX(), e.getY());
   	 	}
		else 
		{
			int selectedTool = myToolSet.getSelectedTool();
			
			// TODO: Yes, this is a mess and ugly code...need to get rid of if structure here in extension
			if(selectedTool == GraphToolset.HAND_TOOL)
				cursorPosition = e.getPoint();
			if(selectedTool == GraphToolset.TRANSLATE_TOOL)
				clickPoint = e.getPoint();
			else if(selectedTool == GraphToolset.ZOOM_IN_TOOL)
				this.zoomIn(true, true);
			else if(selectedTool == GraphToolset.ZOOM_OUT_TOOL)
				this.zoomOut(true, true);
			else if(selectedTool == GraphToolset.ZOOM_IN_Y_TOOL)
				this.zoomIn(false, true);
			else if(selectedTool == GraphToolset.ZOOM_OUT_Y_TOOL)
				this.zoomOut(false, true);
			else if(selectedTool == GraphToolset.ZOOM_IN_X_TOOL)
				this.zoomIn(true, false);
			else if(selectedTool == GraphToolset.ZOOM_OUT_X_TOOL)
				this.zoomOut(true, false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		this.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		int selectedTool = myToolSet.getSelectedTool();
		if(selectedTool == GraphToolset.TRANSLATE_TOOL)
		{
			Point nextPoint = arg0.getPoint();
			
			translateX += (nextPoint.getX() - clickPoint.getX())/this.getWidth();
			translateY += (nextPoint.getY() - clickPoint.getY())/this.getHeight(); 
			
			clickPoint = nextPoint;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
}
