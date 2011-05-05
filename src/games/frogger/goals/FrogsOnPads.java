package games.frogger.goals;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class FrogsOnPads implements IGoal {

	private LevelManager myLevelManager;
	private VoogaPlayField myPlayfield;
	
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		System.out.println(myPlayfield.getSpriteGroup("pad").size());
		return (myPlayfield.getSpriteGroup("pad").getActiveSprite() == null);
	}

	@Override
	public void progress() {
		myLevelManager.getCurrentGame().finish();
		
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		myLevelManager = manager;
		myPlayfield = playfield;
		
		
	}
	
	

}
