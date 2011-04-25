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
     * @param levelManager 
     * @return level completion status
     */
    public boolean checkCompletion (LevelManager levelManager);


    /**
     * Called (and may perform some action) when the goal is achieved
     */
    public void progress ();
    
    
    /**
     * Sets up the goal for this class, saving and calculating any information that will be used later to check goal completion
     * 
     * @param LevelManager for this game
     * @param VoogaPlayField for this game
     */
    public void setupGoal(LevelManager manager, VoogaPlayField playfield);
}
