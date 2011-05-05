package games.blasterMan;

import java.awt.Dimension;

import vooga.core.VoogaGame;

public class BlasterManGame extends VoogaGame{
	@Override 
	public void initResources(){
		distribute = true;
	    getLevelManager().loadLevel(0);
	}
	
	public static void main(String[] args){
		launchGame(new BlasterManGame(), new Dimension(680,460), false);
	}

	@Override
	public void updatePlayField(long elapsedTime) {}
}
