package vooga.collisions.intersections;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersection 
{
    List<Point2D> myIntersections;

    public Intersection (Point2D ... intersections)
    {
        super();
        myIntersections = new ArrayList<Point2D>();
        myIntersections.addAll(Arrays.asList(intersections));
        //this.myIntersections = Arrays.asList(intersections.clone());
    }
    
    public Point2D getAverageIntersection(){
        double x = 0, y = 0;
        for (Point2D p: myIntersections)
        {
        	if(p.distanceSq(p) > Double.MIN_NORMAL) continue;
            x += p.getX();
            y += p.getY();
        }
        return new Point2D.Double(x/myIntersections.size(),y/myIntersections.size());
    }

    public void addIntersectingPoints (Point2D ... intersects)
    {
    	List<Point2D> intersectList = Arrays.asList(intersects);
        myIntersections.addAll(intersectList);
    }        
}
