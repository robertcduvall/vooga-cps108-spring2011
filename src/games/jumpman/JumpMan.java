package games.jumpman;
/**
 * @author David Crowe
 */

import games.jumpman.sprites.Player;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.sprites.spritegroups.SpriteGroup;

public class JumpMan extends VoogaGame {
	
	public static EventManager myEventManager;
	public static final int PLAYER_Y_START = 100;
	public static final int PLAYER_X = 20;
	
	public static void main (String[] args) {
        launchGame(new JumpMan(), new Dimension(750, 400), false);
    }
	
	@Override
	public void updatePlayField(long elapsedTime) {
	}
	
	@Override
	public void initResources() {
		myEventManager = getEventManager();
		
		Player p = new Player(getResourceManager().getImageLoader().getImage("player"), PLAYER_X, PLAYER_Y_START, this);
		getLevelManager().addPlayer(new SpriteGroup<Player>("player", p));
		getLevelManager().loadLevel(0);
	}
}
