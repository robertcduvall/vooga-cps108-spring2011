package games.nemo;

import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class TimesUp implements IGoal {
	
	private VoogaPlayField myPlayfield;
    private LevelManager myLevels;
    
    private LevelTimeManager myLevelTime;
    private long myGoalTime;
    
    public TimesUp(long goal) {
    	myGoalTime = goal;
    }

	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		if (myLevelTime!=null)
			return myLevelTime.getPlayTime() > myGoalTime;
		return false;
	}

	/**
	 * Upgrade level if possible
	 */
	@Override
	public void progress() {
		myLevelTime.updateNumOfLevelsCompleted();
		
		// TODO?
		
		try
        {
            myLevelTime.loadNextLevel();
        }
        catch (LevelException e)
        {
            System.out.println("Congratulations! You win!");
            System.exit(0);  
        }
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		this.myLevels = manager;
		this.myPlayfield = playfield;
	}
	
	/**
	 * Add a level manager that can return play time
	 * @param manager
	 */
	public void addLevelTimeManager(LevelTimeManager manager) {
		this.myLevelTime = manager;
	}

}
