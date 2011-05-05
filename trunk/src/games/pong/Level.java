package games.pong;

import games.pong.sprites.Ball;

import java.util.Collection;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.levels.AbstractLevel;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.PongTargetC;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * @author Michael Ansel
 * @author Wesley Brown
 *
 */
public class Level extends AbstractLevel
{
    public Level (Collection<SpriteGroup<Sprite>> players, VoogaGame game)
    {
        super(players, game);
        
        game.getEventManager().addPeriodicTimer("gravity", 5000, "FirePowerUp", game);
		
		game.registerEventHandler("FirePowerUp", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if((getSpriteGroup("ball").getActiveSprite())!=null) {
            		Ball powerUp = (Ball) addArchetypeSprite("gravity", 300, 300);
            		powerUp.setSpeed(0.2-0.4*Math.random(), 0.2-0.4*Math.random());
            	}
            }            
        });
		
		game.registerEventHandler("BallExits", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	for(Sprite s: getSpriteGroup("powerup").getSprites()) {
            		s.setActive(false);
            	}
            }            
        });
    }

    @Override
    public void loadLevel ()
    {
        addAllSpritesFromPool();
        addBackground();
    }
}
