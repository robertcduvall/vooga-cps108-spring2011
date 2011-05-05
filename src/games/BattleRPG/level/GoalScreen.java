package games.BattleRPG.level;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class GoalScreen implements IGoal
{
	/**
	 * This is an RPG. It has no particular goal except to keep fighting.
	 */
	@Override
	public boolean checkCompletion(LevelManager levelManager)
	{
		//Game has no goal
		return false;
	}

	@Override
	public void progress()
	{
		//N/A
		
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield)
	{
		//N/A
		
	}

}
