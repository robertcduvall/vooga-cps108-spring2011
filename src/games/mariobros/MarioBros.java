package games.mariobros;

import games.mariobros.sprites.Mario;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

public class MarioBros extends VoogaGame
{
	public static EventManager eventManager;
	public static ImageLoader imgLoader;

	@Override
	public void updatePlayField(long elapsedTime)
	{
	}

	@Override
	public void initResources()
	{
		eventManager = getEventManager();
		imgLoader = getImageLoader();

		Mario mario = new Mario(this, getWidth() / 2, getHeight());
		getLevelManager().addPlayer(new SpriteGroup<Mario>("mario", mario));

		getLevelManager().loadLevel(0);
	}

	public static void main(String[] args)
	{
		launchGame(new MarioBros(), new Dimension(640, 480), false);

	}

}
