package games.pacman.goals;

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
	private NoLives lifeGoal;
        
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return lifeGoal.checkCompletion(levelManager) || playfield.getSpriteGroup("dots").size()== 0;
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
        if (lifeGoal.checkCompletion(levels))
        {
            lifeGoal.progress();
            return;
        }
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
        
        lifeGoal = new NoLives();
        lifeGoal.setupGoal(manager, playfield);
        this.playfield = playfield;
        this.levels = manager;
    }

}
