package games.patterson_game.refactoredVooga.resources.path;

import java.awt.Point;

/**
 * A circular path along an arc with a given center and radius,
 * and starting and ending angles.
 * 
 * @author Misha
 *
 */
public class ArcPath extends Path
{
    protected Point center;
    protected double radius;
    protected double angle1, angle2;

    public ArcPath (Point center, double radius, double angle1, double angle2)
    {
        this.center = center;
        this.radius = radius;
        this.angle1 = angle1;
        this.angle2 = angle2;
        
        length = (angle2 - angle1) * radius;
    }
    
    public ArcPath(int x, int y, double radius, double angle1, double angle2)
    {
        this(new Point(x, y), radius, angle1, angle2);
    }


    @Override
    protected PathPoint getPathPointSafe (double distance)
    {
        double t = distance / length;
        double angle = angle2 * t + angle1 * (1-t);
        
        double x = radius * Math.cos(angle);
        double y = radius * Math.sin(angle);
        
        int direction = angle2 > angle1 ? 1 : -1;
        
        return new PathPoint(x + center.x,
                             y + center.y,
                             -direction * y, direction * x);
    }

}
