package vooga.collisions.intersections;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.sound.sampled.Line;

import vooga.collisions.shapes.regularShapes.*;
import vooga.util.math.LineMath;

public class PolygonCircleFinder extends IntersectionFinder<Polygon, Circle>
{

	
		
	

    public PolygonCircleFinder() {
		super();
		c1 = Polygon.class;
		c2 = Circle.class;
	}

	@Override
    public Intersection getIntersection (Polygon p, Circle c)
    {
        Intersection in = new Intersection();
        for(Line2D l: p.getSides()){
            if(c.intersects(l))
                in.addIntersectingPoints(c.findIntersections(l));
        }
        return in;
    }

    @Override
    public boolean areIntersecting (Polygon p, Circle c)
    {
        
        	
        for(Line2D l: p.getSides()){
            if(c.contains(l.getP1()) || c.contains(l.getP2()) || c.contains(LineMath.findMidpoint(l))){
            	
            	return true;
            }
            
        }
        
        return false;
    }

	@Override
	public int compareTo(Object arg0) {
		return this.hashCode() - arg0.hashCode();
	}
    
    

}
