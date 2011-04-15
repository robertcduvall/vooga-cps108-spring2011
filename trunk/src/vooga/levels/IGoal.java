package vooga.levels;

/**
 * An interface that should be implemented by any object that is checking level
 * completion status
 * 
 * @author Andrew Patterson
 */
public interface IGoal
{
    /**
     * Checks the goal's completion status.
     * 
     * @return level completion status
     */
    public boolean checkCompletion ();


    /**
     * Called (and may perform some action) when the goal is achieved
     */
    public void progress ();

}
