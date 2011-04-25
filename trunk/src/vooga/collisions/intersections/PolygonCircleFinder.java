package vooga.collisions.intersections;

import java.awt.geom.Line2D;
import vooga.collisions.shapes.regularShapes.*;

public class PolygonCircleFinder extends IntersectionFinder
{

	
	public PolygonCircleFinder()
	{
		
	}
	
	@Override
	boolean canApply(Class<? extends IShape> c1, Class<? extends IShape> c2) {
		return c1.isAssignableFrom(Polygon.class) && c2.isAssignableFrom(Circle.class);
	}

    @Override
    public Intersection getIntersection (IShape s1, IShape s2)
    {
        Intersection in = new Intersection();
        for(Line2D l: ((Polygon) s1).getSides()){
            if(((Circle)s2).intersects(l))
                in.addIntersectingPoints(((Circle)s2).findIntersections(l));
        }
        return in;
    }

    @Override
    public boolean areIntersecting (IShape s1, IShape s2)
    {
        if(((Polygon)s1).contains(((Circle)s2).getCenter()))
        	return true;
        	
        for(Line2D l: ((Polygon) s1).getSides()){
            if(((Circle)s2).intersects(l))
                return true;
            if(((Circle)s2).contains(l));
            	return true;
        }
        
        return false;
    }

	@Override
	public int compareTo(Object arg0) {
		return this.hashCode() - arg0.hashCode();
	}
    
    

}
