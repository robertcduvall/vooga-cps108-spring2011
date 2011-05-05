package games.mariobros;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import games.mariobros.sprites.Lander;
import vooga.core.event.EventManager;
import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;

/**
 * A goal that checks if all the blocks on the playfield have
 * been destroyed.
 * 
 * @author Misha
 *
 */
public class Landed implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
    	 Lander l = (Lander) playfield.getSpriteGroup("lander").getActiveSprite();
    	 return l.isSafe();
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
        levels.updateNumOfLevelsCompleted();

        Lander l = (Lander) playfield.getSpriteGroup("lander").getActiveSprite();
        l.resetLander();
        
        try
        {
            levels.loadNextLevel();
           
        }
        catch (Exception e)
        {
            System.out.println("You win!");
            levels.getCurrentGame().finish();
            
        }
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
