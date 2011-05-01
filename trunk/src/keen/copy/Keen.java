package keen.copy;

import java.awt.Dimension;

import vooga.core.VoogaGame;

public class Keen extends VoogaGame {

	@Override
	public void updatePlayField(long elapsedTime) {
	}

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
        launchGame(new Keen(), new Dimension(640, 480), false);
	}

}
