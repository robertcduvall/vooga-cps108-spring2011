package vooga.collisions.shapes.util;

import java.awt.geom.Point2D;

import vooga.collisions.shapes.Vertex;
import vooga.collisions.shapes.collisionShapes.BoundingBox;
import vooga.collisions.shapes.regularShapes.Polygon;

public class PolygonMath
{

    public static double getMinX (Polygon p )
    {
        return getMinX(p.getPoints());
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
        return getMinY(p.getPoints());
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
        return findBoundingBox(p.getPoints());
    }

    private static BoundingBox findBoundingBox (Point2D ... vertices)
    {
        
        return new BoundingBox(new Vertex(getMinX(vertices), getMinY(vertices)), new Vertex(getMaxX(vertices), getMaxY(vertices)));
    }
    
    
    public static double getMaxX (Polygon p )
    {
        return getMaxX(p.getPoints());
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
        return getMaxY(p.getPoints());
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
    
    public static Vertex[] createRegularPoylgon (Point2D center,
            int sideNum,
            double sideLength)
 {

		Vertex[] vertices = new Vertex[(int) sideNum];
		double angle = Math.PI / sideNum + Math.PI/2;
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vertex(sideLength * Math.cos(angle)
					+ center.getX(), sideLength * Math.sin(angle)
					+ center.getY());
			angle += 2 * Math.PI / sideNum;

		}

		return vertices;
}

}
