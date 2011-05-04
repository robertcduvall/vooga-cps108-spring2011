package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;
import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.util.PolygonMath;

public class RegularPolygon extends Polygon
{

 

    public RegularPolygon (double x, double y, int sideNum, double sideLength)
    {
    	super(PolygonMath.createRegularPoylgon(new Point2D.Double(x ,y) , sideNum, sideLength));
        center = new Vertex(x,y);
        
    }

   


}
