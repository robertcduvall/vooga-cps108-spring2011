package vooga.debugger.view.grapher;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComponent;

/**
 * Class that contains data about a field for graphing(i.e plot data, color, etc)
 * 
 * @author troyferrell
 */
public class GraphData 
{
	private ArrayList<DataPoint> fieldData = new ArrayList<DataPoint>();
	private DataPoint maxPoint = new DataPoint(Double.MIN_VALUE, Double.MIN_VALUE);
	private DataPoint minPoint = new DataPoint(Double.MAX_VALUE, Double.MAX_VALUE);
	private Color myGraphColor = Color.BLACK;
	private boolean isDrawable = true;
	private DataPoint selectedPoint = new DataPoint();

	public void addData(double x, double y)
	{
		DataPoint newPoint = new DataPoint(x, y);
		fieldData.add(newPoint);
		
		// check Min/Max for time value
		if(newPoint.getX() > maxPoint.getX())
			maxPoint.setX(newPoint.getX());
		else if(newPoint.getX() < minPoint.getX())
			minPoint.setX(newPoint.getX());
		
		// Check Min/Max for data value
		if(newPoint.getY() > maxPoint.getY())
			maxPoint.setY(newPoint.getY());
		else if(newPoint.getY() < minPoint.getY())
			minPoint.setY(newPoint.getY());
	}

	public void clearData()
	{
		fieldData.clear();
	}
	public int getSize()
	{
		return fieldData.size();
	}
	
	public DataPoint getDataPoint(int index)
	{
		return fieldData.get(index);
	}
	
	public Collection<DataPoint> getData()
	{
		return fieldData;
	}
	
	public Color getColor()
	{
		return myGraphColor;
	}
	
	public void setColor(Color c)
	{
		myGraphColor = c;
	}
	
	public DataPoint getMaxPoint() 
	{
		return maxPoint;
	}

	public void setMaxPoint(DataPoint maxPoint) 
	{
		this.maxPoint = maxPoint;
	}
	
	public DataPoint getMinPoint() 
	{
		return minPoint;
	}

	public void setMinPoint(DataPoint minPoint) 
	{
		this.minPoint = minPoint;
	}
	
	public boolean isDrawable() 
	{
		return isDrawable;
	}

	public void setDrawable(boolean isDrawable) 
	{
		this.isDrawable = isDrawable;
	}
	
	public DataPoint getSelectedPoint() 
	{
		return selectedPoint;
	}

	public void setSelectedPoint(DataPoint selectedPoint) 
	{
		this.selectedPoint = selectedPoint;
	}
}
