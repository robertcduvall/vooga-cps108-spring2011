package vooga.collisions.intersections;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

public class Intersection 
{
    List<Point2D> myIntersections;

    public Intersection (Point2D ... intersections)
    {
        super();
        this.myIntersections = Arrays.asList(intersections.clone());
    }
    
    public Point2D getAverageIntersection(){
        double x = 0, y = 0;
        for (Point2D p: myIntersections)
        {
            x += p.getX();
            y += p.getY();
        }
        return new Point2D.Double(x,y);
    }

    public void addIntersectingPoints (Point2D ... intersects)
    {
        myIntersections.addAll(Arrays.asList(intersects));
    }        
}
