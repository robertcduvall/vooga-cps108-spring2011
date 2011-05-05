package games.missileDefense.levels;

import games.breakout.Breakout;
import games.breakout.sprites.Paddle;
import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

/**
 * A level where nothing happens except for a "you win" or "you lose" message. 
 * 
 * @author Misha
 *
 */
public class WinLossLevel extends AbstractLevel
{    
    
    public WinLossLevel (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
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
        Paddle paddle = (Paddle) getSpriteGroup("paddle").getActiveSprite();
        paddle.setActive(false);
        Breakout.eventManager.removeEventHandlers("*");
    }

}
