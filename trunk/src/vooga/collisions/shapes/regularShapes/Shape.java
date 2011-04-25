package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;

public abstract class Shape implements  IShape
{
	protected Vertex center;

	
	/* (non-Javadoc)
     * @see vooga.collisions.shapes.regularShapes.IShape#getCenter()
     */
	@Override
    public Point2D getCenter()
	{
		return center;
	}
	
    /* (non-Javadoc)
     * @see vooga.collisions.shapes.regularShapes.IShape#contains(double, double, double, double)
     */
    @Override
    public boolean contains (double x1, double y1, double x2, double y2)
    {
        return this.contains(x1, y1) && this.contains(x2, y2);
    }
    
    /* (non-Javadoc)
     * @see vooga.collisions.shapes.regularShapes.IShape#setCenter(java.awt.geom.Point2D)
     */
    @Override
    public void setCenter (Point2D.Double center)
    {
    	this.center = new Vertex(center);
    }

}
