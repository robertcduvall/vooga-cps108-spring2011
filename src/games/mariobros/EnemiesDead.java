package games.mariobros;

import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

public class EnemiesDead implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
    	return false;
//        return playfield.getSpriteGroup("enemy").getActiveSprite() == null;
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
        levels.updateNumOfLevelsCompleted();
//        
//        try
//        {
//            levels.loadNextLevel();
//        }
//        catch (LevelException e)
//        {
//            /* TODO win the game better */
//            System.out.println("You win!");
//            System.exit(0);  
//        }
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
