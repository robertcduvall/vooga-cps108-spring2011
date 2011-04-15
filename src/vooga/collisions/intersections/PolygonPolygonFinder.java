package vooga.collisions.intersections;

import java.awt.geom.Line2D;
import vooga.collisions.shapes.regularShapes.*;
import vooga.util.math.LineMath;

public class PolygonPolygonFinder extends IntersectionFinder
{

	@Override
	boolean canApply(Class<? extends IShape> c1, Class<? extends IShape> c2) {
		if (c1.isInstance(Polygon.class) && c2.isInstance(Polygon.class))
		{
			return true;
		}
		
		return false;
	}

    @Override
    public Intersection getIntersection (IShape s1, IShape s2)
    {
        Intersection in = new Intersection();
        for(Line2D L1: ((Polygon) s1).getSides()){
            for(Line2D L2: ((Polygon) s2).getSides()){
                if (L1.intersectsLine(L2))
                    in.addIntersectingPoints(LineMath.findIntersection(L1, L2));
            }
        }
        return in;
    }

    @Override
    public boolean areIntersecting (IShape s1, IShape s2)
    {
        for(Line2D L1: ((Polygon) s1).getSides()){
            for(Line2D L2: ((Polygon) s2).getSides()){
                if (L1.intersectsLine(L2))
                    return true;
            }
        }
        return false;
    }


}
