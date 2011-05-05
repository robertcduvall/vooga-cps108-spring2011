package games.missileDefense;

import games.missileDefense.sprites.Gun;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.physics.forceGenerator.MassProportionalForceGenerator;
import vooga.physics.util.Force;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

public class MissileDefense extends VoogaGame
{
	public static ImageLoader imageLoader;
	
	public static void main(String[] args)
	{
		launchGame(new MissileDefense(), new Dimension(800,800), false);
	}

	@Override
	public void updatePlayField(long elapsedTime) 
	{
		
	}

	@Override
	public void initResources() 
	{
		imageLoader = getImageLoader();
		
		Gun gun  = new Gun(this, this.getWidth() / 2, this.getHeight() - 125);
		getLevelManager().addPlayer(new SpriteGroup<Gun>("gun", gun));
		getLevelManager().loadLevel(0);
	}
}
