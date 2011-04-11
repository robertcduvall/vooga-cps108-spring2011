package vooga.core.event.examples.bubblefishbob.bubbleFish;
public class PathPoint
{
    double x;
    double y;


    public PathPoint (double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    public double getDistTo (PathPoint nextPoint)
    {
        double dx = nextPoint.x - this.x;
        double dy = nextPoint.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
