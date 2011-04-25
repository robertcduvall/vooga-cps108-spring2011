package games.invaders.game;

import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class Invaders extends GameEngine {
	{
		distribute = true;
	}

	public static final int TITLE = 0, GAME_MODE = 1;

	@Override
	public void initResources() {
		nextGameID = TITLE;
		// nextGameID = GAME_MODE;
	}

	@Override
	public GameObject getGame(int GameID) {
		bsSound
				.setBaseRenderer(new com.golden.gamedev.engine.audio.WaveRenderer());
		// TODO: fix goldenT's BaseMusic implementation because sound works, but
		// music doesn't. Why are there even separate BaseMusic and BaseSound
		// classes? With BaseMusic, this has no effect on the number of audio
		// files that can be played simultaneously.
		bsSound.setExclusive(true);
		switch (GameID) {
		case TITLE:
			return new MainMenu(this);
		case GAME_MODE:
			return new InvadersGame(this);
		}
		return null;
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		// TODO: How could VoogaGame be used here?
		// TODO: The Invaders Game has a specific Dimension that it is designed
		// to be played at, so just use that.
		// PlayMusic playMusic = new PlayMusic("theme.wav");
		// playMusic.run();
		game.setup(new Invaders(), new Dimension(800, 600), false);
		game.start();
	}
}
