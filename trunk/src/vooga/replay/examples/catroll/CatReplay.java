package vooga.replay.examples.catroll;

import java.awt.event.KeyEvent;

import vooga.replay.Replay;
import vooga.replay.StateTable;

import com.golden.gamedev.GameEngine;

public class CatReplay extends Replay {

	public CatReplay(GameEngine parent, StateTable table) {
		super(parent, table);
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(keyDown(KeyEvent.VK_ENTER)){
			parent.nextGameID = 0;
			finish();
		}
	}

}
