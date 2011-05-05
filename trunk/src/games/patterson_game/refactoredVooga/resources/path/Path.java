package games.patterson_game.refactoredVooga.resources.path;

import games.patterson_game.refactoredVooga.resources.Direction;

/** 
 * 
 * Defines a two-dimensional path. The path can be accessed by
 * getting a PathPoint by the distance traveled along the path.
 * 
 * @author Misha
 *
 */
public abstract class Path
{
    protected double length = 0;
    
    /**
     * Returns the length of the path, in the same units as
     * the coordinates.
     * 
     * @return The total length of the path
     */
    public double getLength() {return length;}
    
    /**
     * Get information about the point a certain distance along the path.
     * 
     * @param distance The distance from the beginning of the path
     * @return a PathPoint giving the location and direction of the point
     */
    public PathPoint getPathPoint(double distance)
    {
        return getPathPointSafe(Math.max(0,Math.min(length, distance)));
    }
    
    /**
     * Internal method for getPathPoint which ensures that distance
     * is in the range 0...length.
     */
    protected abstract PathPoint getPathPointSafe(double distance);
    
    /**
     * Returns the point at the beginning of the path
     * 
     * @return The starting point of the path
     */
    public PathPoint start()
    {
        return getPathPoint(0);
    }
    
    /**
     * Returns the point at the end of the path
     * 
     * @return The ending point of the path
     */
    public PathPoint end()
    {
        return getPathPoint(length);
    }
    
    /**
     * Returns just the X-coordinate of a location on the path.
     * 
     * @param distance The distance from the beginning of the path
     * @return The x-coordinate at that distance
     */
    public int getX(double distance)
    {
        return getPathPoint(distance).x;
    }

    /**
     * Returns just the Y-coordinate of a location on the path.
     * 
     * @param distance The distance from the beginning of the path
     * @return The y-coordinate at that distance
     */
    public int getY(double distance)
    {
        return getPathPoint(distance).y;
    }
    
    /**
     * Returns just the angle direction of a location on the path.
     * 
     * @param distance The distance from the beginning of the path
     * @return The angle the path is heading in at that distance
     */
    public double getAngle(double distance)
    {
        return getPathPoint(distance).dir;
    }
    
    /**
     * Returns just the direction at a location on the path (the closest
     * compass direction approximation).
     * 
     * @param distance The distance from the beginning of the path
     * @return The direction the path is heading in at that distance
     */
    public Direction getDirection(double distance)
    {
        return getPathPoint(distance).getDirection();
    }
}
