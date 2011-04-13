package vooga.collisions.shapes;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class Side extends Line2D.Double
{

    public Side ()
    {
        super();
    }

    public Side (double x1, double y1, double x2, double y2)
    {
        super(x1, y1, x2, y2);
    }

    public Side (Point2D start, Point2D end)
    {
        super(start, end);
    }
    
    public Side(Line2D s){
        super(s.getP1(), s.getP2());
    }
	
    
    
    
	
}
