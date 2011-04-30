package games.breakout;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
import vooga.levels.AbstractLevel;

public class Level extends AbstractLevel
{
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }

}
