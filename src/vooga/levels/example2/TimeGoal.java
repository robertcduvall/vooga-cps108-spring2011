package vooga.levels.example2;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;

public class TimeGoal implements IGoal
{
    double myStartOfLevelTime;
    LevelManager myLevelManager;
    
    public TimeGoal()
    {
        myStartOfLevelTime = System.currentTimeMillis();
    }
    
    
    /**
     * The level completes 10 seconds after it begins
     */
    @Override
    public boolean checkCompletion ()
    {
        return ((System.currentTimeMillis() - myStartOfLevelTime)/1000.0 >= 10);
    }

    
    /**
     * The level goes to the next level after the current one is complete
     */
    @Override
    public void progress ()
    {
        myLevelManager.loadNextLevel();
    }

}
