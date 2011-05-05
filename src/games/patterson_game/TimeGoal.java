package games.patterson_game;

import games.patterson_game.refactoredVooga.core.VoogaGame;
import games.patterson_game.refactoredVooga.levelsRefactored.IGoal;
import games.patterson_game.refactoredVooga.levelsRefactored.LevelManager;

/**
 * A goal that is complete after a set length of time
 * @author Andrew
 *
 */
public class TimeGoal implements IGoal
{
    private double myStartTime;
    private LevelManager myLevelManager;
    private double myLength;
    
    public TimeGoal()
    {
        super();
        myStartTime = System.currentTimeMillis();
        myLength = AvoiderGame.getBundle().getDouble("time_goal_length");
    }
    
    /**
     * The goal is completed after a set length of time
     */
    @Override
    public GoalStatus checkCompletion ()
    {
        if ((System.currentTimeMillis() - myStartTime) / 1000.0 >= myLength) return GoalStatus.COMPLETE;
        else return GoalStatus.INCOMPLETE;
    }

    
    /**
     * Move on to the next level
     */
    @Override
    public void progress ()
    {
        myLevelManager.updateNumOfLevelsCompleted();        
        myLevelManager.loadNextLevel();
    }

    
    /**
     * It is impossible to fail this goal.  Ship death conditions are checked elsewhere
     */
    @Override
    public void fail ()
    {}


    /**
     * Stores the levelManager for future use
     */
    @Override
    public void setupGoal (VoogaGame game)
    {
        myLevelManager = game.getLevelManager();
    }

}
