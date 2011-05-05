package games.missileDefense.levels;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Paddle;
import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * A goal that checks if all the blocks on the playfield have
 * been destroyed.
 * 
 * @author Misha
 *
 */
public class AllMissilesDestroyed implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    private AllCitiesDestroyed lossGoal;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return lossGoal.checkCompletion(levelManager) || playfield.getSpriteGroup("block").getActiveSprite() == null;
    }


    /**
     * Progress to the next level, if available.
     */
    @Override
    public void progress ()
    {
        if (lossGoal.checkCompletion(levels))
        {
            lossGoal.progress();
            return;
        }
        
        
        levels.updateNumOfLevelsCompleted();
        
        
        Ball b = (Ball) playfield.getSpriteGroup("ball").getActiveSprite();
        Paddle p = (Paddle) playfield.getSpriteGroup("paddle").getActiveSprite();
        
        if (b != null) b.setActive(false);
        p.setBallCount(1 + p.getBallCount());
        p.prepareNewBall();
        
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
        
        lossGoal = new AllCitiesDestroyed();
        lossGoal.setupGoal(manager, playfield);
    }

}
