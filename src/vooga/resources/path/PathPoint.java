package vooga.resources.path;

import java.awt.Point;
import vooga.resources.Direction;

/**
 * 
 * A point with an associated direction. 
 * 
 * See Ezra Miller in the Duke Math department and he'll tell you
 * all about them if you ask him about scheme theory.
 * 
 * <br /><br />
 * 
 * This class extends the Point class so if you don't care for the
 * PathPoint approach and don't need directions, you can treat it
 * as a Point. Similarly to the Point class, its internal variables
 * are public.
 * 
 * @author Misha
 *
 */
public class PathPoint extends Point
{
    private static final long serialVersionUID = 8539303744405390340L;

    /**
     * The angle, as a value in the range [-Pi, Pi). 
     * An angle of 0 corresponds to the positive X direction.
     * An angle of Pi/2 corresponds to the positive Y direction.
     */
    public double dir = 0;

    public PathPoint () {}
    public PathPoint (Point pt) {super(pt);}
    public PathPoint (int x, int y) {super(x, y);}
    
    public PathPoint(int x, int y, double direction)
    {
        super(x, y);
        dir = direction;
        dir = ((dir + Math.PI) % (2 * Math.PI)) - Math.PI;
    }

    public PathPoint(double x, double y, double dx, double dy)
    {
        this((int) x, (int) y, dx, dy);
    }
    
    public PathPoint(int x, int y, double dx, double dy)
    {
        this(x, y, Math.atan2(dy, dx));
    }
    
    /**
     * Convert the angle direction to the Direction object 
     * that most closely approximates it.
     * 
     * @return The direction associated to this point
     */
    public Direction getDirection()
    {
        int simpDir = (int) (4*dir / Math.PI);
        
        switch (simpDir)
        {
            case -3: case -2: 
                return Direction.UP;
            case -1: case 0:  
                return Direction.RIGHT;
            case 1:  case 2:  
                return Direction.DOWN;
            default:
                return Direction.LEFT;
        }
    }

}
