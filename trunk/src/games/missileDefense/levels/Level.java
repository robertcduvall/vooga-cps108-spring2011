package games.missileDefense.levels;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

/**
 * A level of Breakout.
 * 
 * @author Misha
 *
 */
public class Level extends AbstractLevel
{
    public static final int GAME_LEVELS = 2;
    public static final int WIN_LEVEL = 2;
    public static final int LOSS_LEVEL = 3;
    
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
    }

    /**
     * Load the level by loading all the available blocks.
     */
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }

}
