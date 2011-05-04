package games.arrow;

import games.arrow.sprites.Enemy;

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
	
	public VoogaGame myGame;
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        myGame = game;
        game.registerEventHandler("EnemySpawn", new IEventHandler()
        {
                @Override
                public void handleEvent(Object o)
                {
                        spawnEnemy();
                }
        });
        
        game.addPeriodicTimer("OneTime", 5000, "EnemySpawn");
    }

    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }
    
    protected void spawnEnemy() {
    	Sprite enemy = myGame.getLevelManager().addArchetypeSprite("enemy", (int)(getBackground().getWidth()*Math.random()),(int)((getBackground().getHeight()/2)*Math.random()));
		enemy.setBackground(getBackground());
		getSpriteGroup("enemy").addSprites(enemy);
	}
    
   
}
