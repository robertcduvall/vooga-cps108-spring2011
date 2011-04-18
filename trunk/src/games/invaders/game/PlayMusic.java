package games.invaders.game;

import java.awt.Graphics2D;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.engine.BaseAudio;
import com.golden.gamedev.engine.BaseAudioRenderer;
import com.golden.gamedev.engine.BaseIO;

public class PlayMusic extends Thread {
	private final String musicLocation;
	private final Game game;

	public PlayMusic(String musicLocation) {
		this.musicLocation = musicLocation;
		game = new GameEngine() {
			@Override
			public GameObject getGame(int GameID) {
				return new GameObject(null) {
					@Override
					public void update(long elapsedTime) {
					}

					@Override
					public void render(Graphics2D g) {
					}

					@Override
					public void initResources() {
					}
				};
			}
		};
	}

	@Override
	public void run() {
		BaseAudioRenderer renderer = new com.golden.gamedev.engine.audio.WaveRenderer();
		Class base = game.getClass();
		BaseAudio baseAudio = new BaseAudio(new BaseIO(base), renderer);
		baseAudio.play(musicLocation);
		// Stick that in your pipe and smoke it, Golden T!
	}
}
