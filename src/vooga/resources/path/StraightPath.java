package vooga.resources.path;

import java.awt.Point;

/**
 * A straight path with a given starting and ending point.
 * 
 * @author Misha
 */
public class StraightPath extends Path
{
    protected Point start, end;

    protected double dist(Point a, Point b)
    {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }
    
    public StraightPath (Point start, Point end)
    {
        this.start = start;
        this.end = end;
        length = dist(start, end);
    }
    
    public StraightPath(int x1, int y1, int x2, int y2)
    {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    @Override
    protected PathPoint getPathPointSafe (double distance)
    {
        double t = distance / length;
        
        return new PathPoint(end.x * t + start.x * (1-t),
                             end.y * t + start.y * (1-t),
                             end.x - start.x,
                             end.y - start.y);
    }

}
