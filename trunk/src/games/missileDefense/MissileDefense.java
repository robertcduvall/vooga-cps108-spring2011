package games.missileDefense;

import games.missileDefense.sprites.Gun;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

public class MissileDefense extends VoogaGame
{
	public static ImageLoader imageLoader;
	public static EventManager eventManager;
	
	public static void main(String [] args)
	{
		launchGame(new MissileDefense(), new Dimension(800,800), false);
	}

	@Override
	public void updatePlayField(long elapsedTime) 
	{
		// apparently this doesn't do anything anymore
		
	}

	@Override
	public void initResources() 
	{
		eventManager = getEventManager();
		imageLoader = getImageLoader();
		
		Gun gun = new Gun(400, 20, 5, this);
		super.getLevelManager().addPlayer(new SpriteGroup<Gun>("gun", gun));
		//super.getLevelManager().loadLevel(0);
	}
}
