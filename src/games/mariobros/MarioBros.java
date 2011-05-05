package games.mariobros;

import games.asteroids.Asteroid;
import games.mariobros.sprites.Lander;

import java.awt.Dimension;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * IS IT MARIO? OR LUNAR LANDER?
 * @author Ethan Goh
 *
 */
public class MarioBros extends VoogaGame
{
	public static EventManager eventManager;
	public static ImageLoader imageLoader;

	public static void main(String[] args)
	{
		launchGame(new MarioBros(), new Dimension(640, 480), false);
	}

	@Override
	public void updatePlayField(long elapsedTime)
	{
	}

	@Override
	public void initResources()
	{
		eventManager = getEventManager();
		imageLoader = getImageLoader();

		eventManager.registerEventHandler("Game.RandomAsteroid", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				addRandomAsteroid();
			}
		});
		
		Lander mario = new Lander(this, getWidth() / 2, getHeight() / 8);
		getLevelManager().addPlayer(new SpriteGroup<Lander>("lander", mario));
		getLevelManager().loadLevel(0);

	}

	public void addRandomAsteroid()
	{
		Random r = new Random();
		
		Asteroid a = (Asteroid) getLevelManager().addArchetypeSprite("asteroid", -r.nextInt(10), -r.nextInt(10), null);
		
		
	}
}
