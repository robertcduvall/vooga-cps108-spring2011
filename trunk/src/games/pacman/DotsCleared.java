package games.pacman;

import games.pacman.sprites.players.PacMan;
import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * A goal that checks if all the dots on the playfield have
 * been eaten.
 * 
 * @author DJ Sharkey
 *
 */
public class DotsCleared implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
        
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
    	System.out.println("checking comp "+ playfield.getSpriteGroup("dots").size());
        return playfield.getSpriteGroup("dots").size()== 0;
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
		System.out.println("Level comp!");
		levels.updateNumOfLevelsCompleted(); 
		PacMan pm = (PacMan) playfield.getSpriteGroup("pacman").getActiveSprite();
		//set pm pos;
		levels.loadNextLevel();
    }


    /**
     * Prepare the goal for being checked.
     */
    @Override
    public void setupGoal (LevelManager manager, VoogaPlayField playfield)
    {
        this.playfield = playfield;
        this.levels = manager;
    }

}
