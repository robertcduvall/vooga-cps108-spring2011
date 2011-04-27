package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;
import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.util.PolygonMath;

public class RegularPolygon extends Polygon
{

 

    public RegularPolygon (double x, double y, int sideNum, double sideLength)
    {
        center = new Vertex(x,y);
        vertices = PolygonMath.createRegularPoylgon(center, sideNum, sideLength);
    }

   


}
