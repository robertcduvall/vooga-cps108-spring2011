package games.missilecommand.levels;

import java.util.Collection;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

import games.missilecommand.MissileCommandAlex;
import games.missilecommand.sprites.Bomb;

/**
 * A skeleton level. You can't beat it.
 * @author Alex Lee (hl69)
 */
public class Level extends AbstractLevel
{
    
    public static final int LOST_LEVEL = 1;

    private VoogaGame game;
    
    private long interval;

    public Level(Collection<SpriteGroup<Sprite>> players, VoogaGame game) 
    {
        super(players, game);
        this.game = game;
        bindEvents();
        
        /*
         * The level starts off with one bomb dropping every two seconds.
         */
        interval = 2000;
        game.addPeriodicTimer("BombTimer", interval, "DropBomb");
    }

    @Override
    public void loadLevel ()
    {
        addBackground();
        addAllSpritesFromPool();
    }
    
    /**
     * Helper class to register events.
     */
    private void bindEvents()
    {
        game.registerEventHandler("DropBomb", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                spawnBomb();
            }
        });
    }
    
    /**
     * Spawns a bomb at a random x-coordinate, ready to drop down to the city.
     */
    private void spawnBomb()
    {
        Random rng = new Random();
        int x = rng.nextInt(600);
        Bomb b = new Bomb(((MissileCommandAlex)game).loader.getImage("bomb"), x, 0);
        addSpriteToGroup("bomb", b);
    }

}
