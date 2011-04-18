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
    /** Direction pointing to the top of the screen. */
    UP,
    /** Direction pointing to the bottom of the screen. */
    DOWN,
    /** Direction pointing to the left of the screen. */
    LEFT,
    /** Direction pointing to the right of the screen. */
    RIGHT;
    
    /** Alias for UP. */
    public static final Direction NORTH = UP;
    /** Alias for RIGHT. */
    public static final Direction WEST = RIGHT;
    /** Alias for LEFT. */
    public static final Direction EAST = LEFT;
    /** Alias for DOWN. */
    public static final Direction SOUTH = DOWN;

    /** Positive X direction: alias for RIGHT. */
    public static final Direction POS_X = RIGHT;
    /** Negative X direction: alias for LEFT. */
    public static final Direction NEG_X = LEFT;
    /** Positive Y direction: alias for DOWN. */
    public static final Direction POS_Y = DOWN;
    /** Negative Y direction: alias for UP. */
    public static final Direction NEG_Y = UP;    
    
    /**
     * Gets the angle, in degrees, corresponding to a direction.
     * 
     * @return The angle.
     */
    public int getAngle()
    {
        switch(this)
        {
            case UP: return 270;
            case DOWN: return 90;
            case LEFT: return 180;
            case RIGHT: return 0;
            default: return 0;
        }
    }
}
