package vooga.resources;

/**
 * 
 * One of the four compass directions; assumes coordinates in which 
 * DOWN (or SOUTH) is the direction of increasing Y, and
 * RIGHT (or WEST) is the direction of increasing X.
 * 
 * @author Misha
 *
 */
public enum Direction
{
    UP,
    DOWN,
    LEFT,
    RIGHT;
    
    public static final Direction NORTH = UP, WEST = RIGHT, EAST = LEFT, SOUTH = DOWN;
    public static final Direction POS_X = RIGHT, NEG_X = LEFT, POS_Y = DOWN, NEG_Y = UP;
    
    public double getAngle()
    {
        switch(this)
        {
            case UP: return -Math.PI/2;
            case DOWN: return Math.PI/2;
            case LEFT: return Math.PI;
            case RIGHT: return 0;
            default: return 0;
        }
    }
}
