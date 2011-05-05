package games.missilecommand.levels;

import vooga.levels.IGoal;
import vooga.levels.LevelManager;
import vooga.levels.VoogaPlayField;
import vooga.sprites.improvedsprites.Sprite;
import games.missilecommand.sprites.Turret;

/**
 * A goal representing the player's loss.
 * @author Alex Lee (hl69)
 */
public class CityDestroyed implements IGoal
{

    private VoogaPlayField playfield;
    private LevelManager levels;

    /**
     * Check whether there are any remaining buildings.
     */
    @Override
    public boolean checkCompletion(LevelManager levelManager)
    {
        return playfield.getSpriteGroup("building").getActiveSprite() == null;
    }

    /**
     * If there are no more remaining buildings, the player has lost. Get rid
     * of all the sprites and advance the player to the losing screen.
     */
    @Override
    public void progress()
    {
        Turret turret =
            (Turret)playfield.getSpriteGroup("turret").getActiveSprite();
        if(turret != null) turret.setActive(false);

        for(Sprite missile : playfield.getSpriteGroup("bomb").getSprites())
        {
            if (missile != null)
                missile.setActive(false);
        }

        for(Sprite city : playfield.getSpriteGroup("building").getSprites())
        {
            if (city != null)
                city.setActive(false);
        }

        levels.loadLevel(Level.LOST_LEVEL);
    }

    /**
     * Associate some useful objects.
     */
    @Override
    public void setupGoal (LevelManager manager, VoogaPlayField playfield)
    {
        this.playfield = playfield;
        this.levels = manager;
    }

}
