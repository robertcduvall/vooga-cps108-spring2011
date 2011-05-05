package games.missileDefense.levels;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
/**
 * when all the missles have fallen or been destroyed
 * @author johnegan
 *
 */
public class MissilesGone implements IGoal
{
	private VoogaPlayField playfield;
    private LevelManager levels;
    
    private PlayerIsOutOfLives outOfLives;
    private CitiesDead citiesDead;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        return outOfLives.checkCompletion(levelManager) || false || playfield.getSpriteGroup("missile").getActiveSprite() == null;
    }

	@Override
	public void progress() {
		if (outOfLives.checkCompletion(levels))
        {
            outOfLives.progress();
            return;
        }
		
		if (outOfLives.checkCompletion(levels))
        {
            citiesDead.progress();
            return;
        }

        levels.updateNumOfLevelsCompleted();

        if(levels.getNumOfLevelsCompleted() == 2)
        {
        	levels.getCurrentGame().finish();
        }
        levels.loadNextLevel();
	}

	@Override
	public void setupGoal(LevelManager manager, VoogaPlayField playfield) 
	{
		this.playfield = playfield;
        this.levels = manager;
        
        outOfLives = new PlayerIsOutOfLives();
        outOfLives.setupGoal(manager, playfield);
	}
}
