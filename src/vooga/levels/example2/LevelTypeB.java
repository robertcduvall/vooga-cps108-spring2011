package vooga.levels.example2;

import java.util.Collection;
import com.golden.gamedev.Game;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A basic level type which initializes all sprites
 */
public class LevelTypeB extends AbstractLevel {

    public LevelTypeB (Collection<Sprite> players, Game game)
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
