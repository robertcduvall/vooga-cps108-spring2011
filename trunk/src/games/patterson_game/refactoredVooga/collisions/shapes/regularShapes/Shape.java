package games.patterson_game.refactoredVooga.collisions.shapes.regularShapes;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public abstract class Shape implements  IShape
{
	@Override
	public Double setAngle(double angle) {
		return rotate(angle-myAngle);
	}

	@Override
	public Double getAngle() {
		return myAngle;
	}
	
	@Override
	public Double rotate(double dAngle) {
		
		shapeRotate(dAngle);
		return myAngle = (myAngle + dAngle) %360;
	}

	protected abstract void shapeRotate(double dAngle);
	

	protected Vertex center;
	protected double myAngle;
	
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
    
    @Override
    public void setCenter (double x,double y)
    {
    	this.center = new Vertex(x,y);
    }

}
