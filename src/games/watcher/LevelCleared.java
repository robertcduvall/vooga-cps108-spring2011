package games.watcher;

import com.golden.gamedev.object.Sprite;

import games.bigfish.PlayerFish;
import games.watcher.sprites.Children;
import games.watcher.sprites.Leader;
import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * A goal that checks if all the blocks on the playfield have
 * been destroyed.
 * 
 * @author Conrad based off of Misha Design
 *
 */
public class LevelCleared implements IGoal
{   
    private static VoogaPlayField myPlayfield;
    private LevelManager myLevelManager;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager){
    	
    	for(vooga.sprites.improvedsprites.Sprite player : myPlayfield.getSpriteGroup("leader").getSprites()){
			return ((Leader) player).checkCount();
		}
		return false;
    
    }

	/**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
        myLevelManager.updateNumOfLevelsCompleted();
        try
        {
            myLevelManager.loadNextLevel();
        }
        catch (LevelException e)
        {
            /* TODO win the game better */
            System.out.println("You win!");
            myLevelManager.loadNextLevel();
        }
    }
    
    /**
     * Prepare the goal for being checked.
     */
    @Override
    public void setupGoal (LevelManager manager, VoogaPlayField playfield)
    {
        this.myPlayfield = playfield;
        this.myLevelManager = manager;
    }

}
