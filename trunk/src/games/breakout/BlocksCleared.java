package games.breakout;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * A goal that checks if all the blocks on the playfield have
 * been destroyed.
 * 
 * @author Misha
 *
 */
public class BlocksCleared implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return playfield.getSpriteGroup("block").getActiveSprite() == null;
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
        levels.updateNumOfLevelsCompleted();
        
        
        Ball b = (Ball) playfield.getSpriteGroup("ball").getActiveSprite();
        Paddle p = (Paddle) playfield.getSpriteGroup("paddle").getActiveSprite();
        
        if (b != null) b.setActive(false);
        p.prepareNewBall();
        p.setBallCount(1 + p.getBallCount());
        
        try
        {
            levels.loadNextLevel();
        }
        catch (LevelException e)
        {
            /* TODO win the game better */
            System.out.println("You win!");
            System.exit(0);  
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
