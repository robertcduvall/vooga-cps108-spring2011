package games.blasterMan;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.replay.StateTable;
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
	@Override 
	public void initResources(){
		distribute = true;
	    getLevelManager().loadLevel(0);
	    st = new StateTable(this);
	    this.registerEventHandler("Input.Replay", new IEventHandler(){
	    	@Override
	    	public void handleEvent(Object o){
	    		replay();
	    	}
	    });
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
