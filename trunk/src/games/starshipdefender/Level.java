package games.starshipdefender;

import java.util.Collection;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

public class Level extends AbstractLevel
{
    
    public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
    }

    @Override
    public void loadLevel()
    {
        addAllSpritesFromPool();
        addBackground();
    }

}
