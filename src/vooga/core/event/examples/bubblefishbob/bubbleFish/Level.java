package vooga.core.event.examples.bubblefishbob.bubbleFish;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Michael Ansel
 */
public class Level
{

    private int myFishToSave;
    private ArrayList<PathPoint> myPathPoints;
    private double myPathSpeed;
    private int myStartT;


    public Level (int startT,
                  int fishToSave,
                  double pathSpeed,
                  ArrayList<PathPoint> pathPoints)
    {
        myPathPoints = new ArrayList<PathPoint>(pathPoints);
        myPathSpeed = pathSpeed;
        myStartT = startT;
        myFishToSave = fishToSave;
    }


    public int getFishToSave ()
    {
        return myFishToSave;
    }


    public List<PathPoint> getPath ()
    {
        return myPathPoints;
    }


    public int getPathLength ()
    {
        return myPathPoints.size();
    }


    public double getPathSpeed ()
    {
        return myPathSpeed;
    }


    public int getStartT ()
    {
        return myStartT;
    }


    public void setPathSpeed (double d)
    {
        myPathSpeed = d;
    }
}
