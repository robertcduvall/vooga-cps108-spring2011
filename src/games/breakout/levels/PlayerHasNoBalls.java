package games.breakout.levels;

import games.breakout.Breakout;
import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import vooga.levels.IGoal;
import vooga.levels.LevelException;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A goal that checks if the player has no balls.
 * 
 * @author Misha
 *
 */
public class PlayerHasNoBalls implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        Paddle paddle = (Paddle) playfield.getSpriteGroup("paddle").getActiveSprite(); 
        
        if (paddle == null) return false;
        
        Ball ball = (Ball) playfield.getSpriteGroup("ball").getActiveSprite();

        return ball == null && paddle.getBallCount() == 0;
    }


    /**
     * Lose the game.
     */
    @Override
    public void progress ()
    {
        Ball b = (Ball) playfield.getSpriteGroup("ball").getActiveSprite();
        
        if (b != null) b.setActive(false);

        for(Sprite block : playfield.getSpriteGroup("block").getSprites())
        {
            if (block != null)
                block.setActive(false);
        }
        
        levels.loadLevel(Level.LOSS_LEVEL);
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
