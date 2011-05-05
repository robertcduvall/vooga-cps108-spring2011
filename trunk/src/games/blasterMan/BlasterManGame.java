package games.blasterMan;

import games.blasterMan.sprites.PlayerType;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.replay.StateTable;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;
/** 
 * Blasterman!!! 
 * <p>Use arrow keys to move around. Press space to shoot. Press Escape to replay your awesomeness. 
 * <p>Survive the level for 60 
 * seconds and you win!!
 * 
 * @author Josue
 *
 */
public class BlasterManGame extends VoogaGame{
	private StateTable st;
	private PlayerType player;
	@Override 
	public void initResources(){
		distribute = true;
		player = new PlayerType(this, getImageLoader().getImage("blasterman", Direction.WEST));
		player.setLocation(300, 200);
		this.getLevelManager().addPlayer(new SpriteGroup<PlayerType>("blasterman", player));
	    getLevelManager().loadLevel(0);
	    st = new StateTable(this);
	    this.registerEventHandler("Input.Replay", new IEventHandler(){
	    	@Override
	    	public void handleEvent(Object o){
	    		replay();
	    	}
	    });
	}
	public PlayerType getPlayer(){
		return player;
	}
	public void replay(){
		st.replayTable(this);
	}
	
	public static void main(String[] args){
		launchGame(new BlasterManGame(), new Dimension(680,460), false);
	}

	@Override
	public void updatePlayField(long elapsedTime) {}
}
