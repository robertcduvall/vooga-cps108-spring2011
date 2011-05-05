package games.pong;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import games.pong.sprites.AbstractPaddle;
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
public class BlankGoal implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
    	AbstractPaddle player = (AbstractPaddle) playfield.getSpriteGroup("PlayerPaddle").getActiveSprite();
    	AbstractPaddle computer = (AbstractPaddle) playfield.getSpriteGroup("AIPaddle").getActiveSprite();
    	return player.isDead() || computer.isDead();
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
       System.out.println("game over");
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
