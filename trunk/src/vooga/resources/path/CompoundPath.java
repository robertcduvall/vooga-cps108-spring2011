package vooga.resources.path;

import java.awt.Point;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A path composed of multiple segments which
 * are, themselves, Paths. The segments need not 
 * necessarily be connected.
 * 
 * @author Misha
 *
 */
public class CompoundPath extends Path
{
    protected SortedMap<Double, Path> paths;
    
    public CompoundPath()
    {
        paths = new TreeMap<Double, Path>();
        length = 0;
    }
    
    public CompoundPath(Path ...paths)
    {
        this();
        for(Path p : paths)
        {
            addPath(p);
        }
    }

    /**
     * Append a path segment to the end of this path.
     * 
     * @param p The segment to add.
     */
    public void addPath (Path p)
    {
        paths.put(length, p);
        length += p.getLength();
    }
    
    /**
     * Add a straight segment from the current end of 
     * this path to a point.
     * 
     * @param pt The new ending point of this path.
     */
    public void addSegmentTo (Point pt)
    {
        addPath(new StraightPath(end(), pt));
    }

    @Override
    protected PathPoint getPathPointSafe (double distance)
    {
        double previousPathDist = paths.headMap(distance).lastKey();
        
        Path currentPath = paths.get(previousPathDist);
        
        return currentPath.getPathPoint(distance - previousPathDist);
    }

}
