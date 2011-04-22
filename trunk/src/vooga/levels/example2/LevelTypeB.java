package vooga.levels.example2;

import java.util.Collection;
import com.golden.gamedev.Game;
import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * A basic level type which initializes all sprites
 */
public class LevelTypeB extends AbstractLevel {

    public LevelTypeB (SpriteGroup players, VoogaGame game)
    {
        super(players, game);
    }

    /**
     * Adds all sprites from the pool to the playingfield
     */
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
    }

}
