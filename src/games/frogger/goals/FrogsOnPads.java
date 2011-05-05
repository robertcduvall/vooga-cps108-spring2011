package games.frogger.goals;

import games.frogger.Frogger;
import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class FrogsOnPads implements IGoal {

	private LevelManager myLevelManager;
	private VoogaPlayField myPlayfield;
	
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		return (myPlayfield.getSpriteGroup("pad").getActiveSprite() == null && myPlayfield.getSpriteGroup("pad").size() == 0);
	}

	@Override
	public void progress() {
		myLevelManager.updateNumOfLevelsCompleted();
		myPlayfield.clearPlayField();
		if (myLevelManager.getNumOfLevelsCompleted() == myLevelManager.getNumOfLevels()) {
			myLevelManager.getCurrentGame().finish();
		}
		Frogger.myEventManager.fireEvent(this, "Game.NextFrog");
		
		try{
			myLevelManager.loadLevel(myLevelManager.getCurrentLevel().getId() + 1);
		} catch (LevelException e){
			// Keeps trying to load a new level even though we finish all of them right now...
			// Not sure why....
			myLevelManager.getCurrentGame().finish();
		}
		
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		myLevelManager = manager;
		myPlayfield = playfield;
		
		
	}
	
	

}
