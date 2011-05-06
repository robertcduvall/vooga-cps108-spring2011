/**
 * 
 */
package games.tao_rpsallstars.levels;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * @author Kevin
 *
 */
public class ReachedScore implements IGoal{

	/* (non-Javadoc)
	 * @see vooga.levels.IGoal#checkCompletion(vooga.levels.LevelManager)
	 */
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		// TODO Check if there your score surpasses 7
		return false;
	}

	/* (non-Javadoc)
	 * @see vooga.levels.IGoal#progress()
	 */
	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see vooga.levels.IGoal#setupGoal(vooga.levels.LevelManager, vooga.levels.VoogaPlayField)
	 */
	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		// TODO Auto-generated method stub
		
	}
	

}
