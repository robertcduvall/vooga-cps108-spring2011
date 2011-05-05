package games.patterson_game.refactoredVooga.collisions.shapes.regularShapes;

import games.patterson_game.refactoredVooga.collisions.shapes.Vertex;
import games.patterson_game.refactoredVooga.collisions.shapes.util.PolygonMath;
import java.awt.geom.Point2D;

public class RegularPolygon extends Polygon
{

 

    public RegularPolygon (double x, double y, int sideNum, double sideLength)
    {
        center = new Vertex(x,y);
        vertices = PolygonMath.createRegularPoylgon(center, sideNum, sideLength);
    }

   


}
