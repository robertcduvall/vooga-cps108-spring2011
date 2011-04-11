package vooga.levels.example.levels;

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
