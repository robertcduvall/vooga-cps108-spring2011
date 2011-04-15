package vooga.collisions.shapes.regularShapes;

import java.awt.geom.Point2D;
import vooga.collisions.shapes.Vertex;

public class RegularPolygon extends Polygon
{

 

    public RegularPolygon (double x, double y, int sideNum, double sideLength)
    {
        center = new Vertex(x,y);
        vertices = RegularPolygon.createRegularPoylgon(center, sideNum, sideLength);
    }

    public static Vertex[] createRegularPoylgon (Point2D center,
                                                  int sideNum,
                                                  double sideLength)
    {
        
        Vertex[] vertices = new Vertex[(int) sideNum];
        double angle = Math.PI/sideNum;
        for (int i = 0; i < vertices.length; i++ ){
            vertices[i] = new Vertex(sideLength*Math.cos(angle)+center.getX(), sideLength*Math.sin(angle)+center.getY());
            angle += 2*Math.PI/sideNum;
            
        }
     
        return vertices;
    }

    @Override
    public void rotate (double degrees)
    {
        // TODO Auto-generated method stub

    }

}
