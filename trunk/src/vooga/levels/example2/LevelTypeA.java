package vooga.levels.example2;

import java.util.Collection;
import com.golden.gamedev.Game;
import vooga.core.VoogaGame;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * A basic level type which initializes all blueEnemy sprites
 */
public class LevelTypeA extends AbstractLevel {

    public LevelTypeA (SpriteGroup players, VoogaGame game)
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
