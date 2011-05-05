package games.patterson_game.refactoredVooga.levelsRefactored;

import games.patterson_game.refactoredVooga.core.VoogaGame;


/**
 * An interface that signals that this object contains goal conditions
 * 
 * @author Andrew Patterson
 */
public interface IGoal
{
    /**
     * <code>COMPLETE</code> means that the goal is complete;
     * <code>NOT_YET_COMPLETED</code> means that the goal has not yet been completed but still has a chance of being finished.
     * <code>FAILED</code> means that the goal's objective was not achieved and completion of it is no longer possible. 
     */
    public enum GoalStatus
    {
        COMPLETE, INCOMPLETE, FAILED
    }
    
    
    /**
     * Checks the goal's completion status.
     *  
     * @return level completion status
     */
    public GoalStatus checkCompletion ();


    /**
     * Defines what action (if any) should be take once the goal is complete
     */
    public void progress ();
    
    
    /**
     * Defines what action (if any) should be take if the goal is failed
     */
    public void fail ();


    /**
     * Sets up the goal for this class, saving and calculating any information
     * that will be used later to check goal completion
     * 
     * @param game the <code>VoogaGame</code> for this <code>IGoal</code>
     */
    public void setupGoal(VoogaGame game);
}
