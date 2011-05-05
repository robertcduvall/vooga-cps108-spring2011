package games.jumper.levelstuff;

import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
/**
 * Establishes the goal for game. The goal is to reach the door, if the door is reached
 * load the next level.
 * @author Charlie Hatcher
 *
 */
public class DoorReached implements IGoal{
	 private VoogaPlayField myPlayfield;
	 private LevelManager myLevelManager;
	 
	/**
	 * Checks the to see if the level has been completed.
	 */
	@Override
	public boolean checkCompletion(LevelManager levelManager) {
		return myPlayfield.getSpriteGroup("door").getActiveSprite()==null;
	}

	/**
	 * If the level is completed, load the next level. If the level is complete
	 * set the player's position at the starting position. If no more levels are 
	 * available quit the game and congratulate the player.
	 */
	@Override
	public void progress() {
		 try
	        {
			 	myPlayfield.getSpriteGroup("avatar").getActiveSprite().setLocation(100, 300);
	            myLevelManager.loadNextLevel();
	        }
	        catch (LevelException e)
	        {
	            /*
	             * TODO: Set up a better way to handle a win.
	             */
	            System.out.println("You win!");
	            myLevelManager.getCurrentGame().finish();  
	        }
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) {
		this.myLevelManager = manager;
		this.myPlayfield=playfield;
	}

}