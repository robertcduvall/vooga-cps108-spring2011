package games.mariobros;

import java.awt.Dimension;

import vooga.core.VoogaGame;

public class MarioBros extends VoogaGame {

	@Override
	public void updatePlayField(long elapsedTime) {
	}

	@Override
	public void initResources() {
	
	}
	
	public static void main(String[] args) {
        launchGame(new MarioBros(), new Dimension(640, 480), false);
	}

}
