package games.bigfish;


/**
 * Big fish, little fish; the game!
 * 
 * Eat fishes smaller than you to get bigger, and become the biggest fish in the sea!
 * 
 * @author CJAMES
 */

import games.breakout.sprites.Paddle;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;

public class BigFish extends VoogaGame {

	
	public static EventManager eventManager;
    public static ImageLoader imageLoader;
	
	public static void main (String[] args)
    {
        launchGame(new BigFish(), new Dimension(640, 480), false);
    }
	
	
	@Override
	public void updatePlayField(long elapsedTime) {
	}

	@Override
	public void initResources() {

        eventManager = getEventManager();
        eventManager.addPeriodicTimer("period1", 4000, "spawnFish");
        eventManager.registerEventHandler("spawnFish", new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				spawnRandomFish();
			}
        });
		
        imageLoader = getImageLoader();
        
        PlayerFish myFish = new PlayerFish(this,getWidth()/2,getHeight()/2,1);
        getLevelManager().addPlayer(new SpriteGroup<PlayerFish>("playerFish", myFish));
        getLevelManager().loadLevel(0);
		
	}


	protected void spawnRandomFish() {
		Random gen = new Random();
		int size = gen.nextInt(7);
		//BufferedImage fishImage = this.getImageLoader().getImage("fish"+size,Direction.WEST);
		getLevelManager().addArchetypeSprite("enemyFish"+size, 0, 
				gen.nextInt(getHeight()));
			
	}

}
