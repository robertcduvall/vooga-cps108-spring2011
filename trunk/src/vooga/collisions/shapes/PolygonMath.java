package vooga.collisions.shapes;

import java.awt.geom.Point2D;

public class PolygonMath
{

    public static double getMinX (Polygon p )
    {
        return getMinX(p.getVertices());
    }
    
    public static double getMinX (Point2D ... vertices)
    {
        double minX = vertices[0].getX();
        for (Point2D v: vertices){
            if (v.getX() < minX)
                minX = v.getX();
        }
        
        return minX;
    }

    public static double getMinY (Polygon p )
    {
        return getMinY(p.getVertices());
    }
    
    public static double getMinY (Point2D ... vertices)
    {
        
        double minY = vertices[0].getY();
        for (Point2D v: vertices){
            if (v.getY() < minY)
                minY = v.getY();
        }
        
        return minY;
    }
    
    
    public static BoundingBox findBoundingBox(Polygon p){
        return findBoundingBox(p.getVertices());
    }

    private static BoundingBox findBoundingBox (Point2D ... vertices)
    {
        
        return new BoundingBox(new Vertex(getMinX(vertices), getMinY(vertices)), new Vertex(getMaxX(vertices), getMaxY(vertices)));
    }
    
    
    public static double getMaxX (Polygon p )
    {
        return getMaxX(p.getVertices());
    }

    private static double getMaxX (Point2D... vertices)
    {
        double maxX = vertices[0].getX();
        for (Point2D v: vertices){
            if (v.getX() > maxX)
                maxX = v.getX();
        }
        
        return maxX;
        
    }

    
    public static double getMaxY (Polygon p )
    {
        return getMaxY(p.getVertices());
    }
    
    private static double getMaxY (Point2D ... vertices)
    {
        
        double maxY = vertices[0].getY();
        for (Point2D v: vertices){
            if (v.getY() > maxY)
                maxY = v.getY();
        }
        
        return maxY;
    }

}
