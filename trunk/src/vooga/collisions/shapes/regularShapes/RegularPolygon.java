package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;
import vooga.collisions.shapes.Vertex;

public class RegularPolygon extends Polygon
{

 

    public RegularPolygon (double x, double y, double sideNum, double sideLength)
    {
        center = new Vertex(x,y);
        vertices = RegularPolygon.createRegularPoylgon(center, sideNum, sideLength);
    }

    public static Vertex[] createRegularPoylgon (Point2D center,
                                                  double sideNum,
                                                  double sideLength)
    {
        
        //TODO: design this method
        
        
        
        
        
        return null;
    }

    @Override
    public void rotate (double degrees)
    {
        // TODO Auto-generated method stub

    }

}
