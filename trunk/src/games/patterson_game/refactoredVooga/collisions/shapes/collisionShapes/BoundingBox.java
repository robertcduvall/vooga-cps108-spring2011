package games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes;
import games.patterson_game.refactoredVooga.collisions.shapes.util.PolygonMath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;



public class BoundingBox extends Rectangle2D.Double
{

    public BoundingBox ()
    {
        super();
    }

    public BoundingBox (double x, double y, double w, double h)
    {
        super(x, y, w, h);
    }

	public BoundingBox(Point2D p, double width, double height)
	{
	    super(p.getX(), p.getY(), width, height );
	}

    public BoundingBox (Point2D v1, Point2D v2)
    {
        super(PolygonMath.getMinX(v1,v2), 
              PolygonMath.getMinY(v1, v2),
              Math.abs(v1.getX() - v2.getX()),
              Math.abs(v1.getY() - v2.getY()));
    }
    
    public BoundingBox(Rectangle2D r1)
    {
    	super(r1.getX(), r1.getY(), r1.getWidth(), r1.getHeight());
    }
}
