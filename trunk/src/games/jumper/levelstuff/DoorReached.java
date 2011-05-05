package games.jumper.levelstuff;

import vooga.levels.IGoal;
import vooga.levels.LevelException;
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
		 try
	        {
			 	playfield.getSpriteGroup("avatar").getActiveSprite().setLocation(100, 300);
	            levels.loadNextLevel();
	        }
	        catch (LevelException e)
	        {
	            /* TODO win the game better */
	            System.out.println("You win!");
	            System.exit(0);  
	        }
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		this.levels = manager;
		this.playfield=playfield;
	}

}