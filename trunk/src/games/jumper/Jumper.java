package games.jumper;



import games.jumper.sprites.Avatar;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.resources.images.ImageLoader;
import vooga.sprites.spritegroups.SpriteGroup;
/**
 * The game file for Jumper
 * 
 * @author Charlie Hatcher
 */
public class Jumper extends VoogaGame{

	{distribute = true;}
	
	public static EventManager myEventManager;
    public static ImageLoader myImageLoader;
    private Avatar myAvatar;
    
	public static void main(String[] args){
		launchGame(new Jumper(), new Dimension(700,400),false);
	}
	@Override
	public void updatePlayField(long elapsedTime) {
		
	}
		
	/**
	 * Launches the game with the given avatar as the player.
	 */
	@Override
	public void initResources() {
		myEventManager = getEventManager();
		myImageLoader = getImageLoader();
		
		myAvatar = new Avatar(this);
		getLevelManager().addPlayer(new SpriteGroup<Avatar>("avatar", myAvatar));
		getLevelManager().loadLevel(0);
	
	}

}

