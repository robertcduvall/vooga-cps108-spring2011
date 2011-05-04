package games.pacman;


import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * A goal that checks if all the dots on the playfield have
 * been eaten.
 * 
 * @author DJ SHarkey
 *
 */
public class DotsCleared implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		// TODO Auto-generated method stub
		
	}
    
 

}
