package vooga.levels.example2;

import java.util.Collection;
import com.golden.gamedev.Game;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;

/**
 * A basic level type which initializes all blueEnemy sprites
 */
public class LevelTypeA extends AbstractLevel {

    public LevelTypeA (Collection<Sprite> players, Game game)
    {
        super(players, game);
    }

    /**
     * Add all blueEnemy sprites from the pool to the playingfield
     */
    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool("blueEnemy");
    }

}
