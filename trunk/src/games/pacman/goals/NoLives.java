package games.pacman.goals;


import games.pacman.sprites.players.PacMan;
import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;

/**
 * Check if Pacman is out of lives.
 * 
 * @author DJ Sharkey
 *
 */
public class NoLives implements IGoal
{   
    private VoogaPlayField playfield;
    private LevelManager levels;
    
    /**
     * Check whether the goal is completed.
     */
    @Override
    public boolean checkCompletion (LevelManager levelManager)
    {
        PacMan pm = (PacMan) playfield.getSpriteGroup("pacman").getActiveSprite(); 
        
        if (pm == null) return false;
        return pm.getLives() <= 0;
    }


    /**
     * Lose the game.
     */
    @Override
    public void progress ()
    {   
    	levels.getCurrentGame().fireEvent(this, "GameOver");
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
