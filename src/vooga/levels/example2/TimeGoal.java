package vooga.levels.example2;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;

public class TimeGoal implements IGoal
{
    private double myStartOfLevelTime;
    private LevelManager myLevelManager;
    
    public TimeGoal()
    {
        myStartOfLevelTime = System.currentTimeMillis();
    }

    
    /**
     * The level goes to the next level after the current one is complete
     */
    @Override
    public void progress ()
    {
        myLevelManager.loadNextLevel();
    }


	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		 return ((System.currentTimeMillis() - myStartOfLevelTime)/1000.0 >= 10);
	}

}
