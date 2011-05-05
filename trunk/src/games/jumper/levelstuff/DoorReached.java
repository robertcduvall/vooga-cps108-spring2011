package games.jumper.levelstuff;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class DoorReached implements IGoal{
	 private VoogaPlayField playfield;
	 private LevelManager levels;
	 
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		return playfield.getSpriteGroup("door").getActiveSprite()==null;
	}

	@Override
	public void progress() {
		
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		this.levels = manager;
		this.playfield=playfield;
	}

}