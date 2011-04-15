package vooga.collisions.shapes.regularShapes;

import vooga.collisions.shapes.Vertex;

public class Quadrilateral extends Polygon
{

    public Quadrilateral (Vertex v1, Vertex v2, Vertex v3, Vertex v4)
    {
        super(new Vertex[]{v1,v2,v3,v4});
    }

}
