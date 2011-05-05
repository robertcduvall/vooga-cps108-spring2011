package games.asteroids;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * @author Michael Ansel
 * @author Wesley Brown
 *
 */
public class Level extends AbstractLevel
{
    private VoogaGame myGame;

	public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        myGame = game;
        game.registerEventHandler("EnemySpawn", new IEventHandler()
        {
                @Override
                public void handleEvent(Object o)
                {
                        spawnAsteroid();
                }
        });
        
        game.addPeriodicTimer("OneTime", 2000, "EnemySpawn");
    }

    protected void spawnAsteroid() {
    	double x = this.getBackground().getWidth()*Math.random();
    	double y = this.getBackground().getHeight()*Math.random();
    	Sprite enemy = myGame.getLevelManager().addArchetypeSprite("asteroid", (int)x,(int)y);
		getSpriteGroup("asteroid").addSprites(enemy);
	}

	@Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }
}
