package games.leapingSquirrel;

import games.leapingSquirrel.sprites.Squirrel;
import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;


public class Level extends AbstractLevel
{

    private VoogaGame lsGame;


    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        this.lsGame = game;

        lsGame.registerEventHandler("Platform.Destroy", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                addArchetypeSprite((String) o,
                                   getRandomColumn(),
                                   0);
            }
        });

        lsGame.registerEventHandler("Game.NewSquirrel", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                addSpriteToGroup("squirrel",
                                 new Squirrel(lsGame.getImageLoader()
                                                    .getImage("Squirrel_Right"),
                                              LeapingSquirrelGame.SQUIRREL_START_X,
                                              750,
                                              lsGame));
            }
        });

    }


    @Override
    public void loadLevel ()
    {
        addBackground();
        addAllSpritesFromPool();
    }


    /**
     * Returns x coordinate for new platform generation
     */
    private int getRandomColumn ()
    {
        int[] xCols = {50, 300, 550};
        int choice = (int) Math.ceil(Math.random()*3);
        return xCols[choice];
    }
}
