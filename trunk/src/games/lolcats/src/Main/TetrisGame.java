package games.lolcats.src.Main;

import games.lolcats.src.Context.ITetrisContext;
import games.lolcats.src.Helper.PixelPerfectCollision;
import games.lolcats.src.Helper.ResourceManager;
import games.lolcats.src.Helper.SpriteLayerComparator;
import games.lolcats.src.KeyActions.IKeyAction;
import games.lolcats.src.Minos.Tetromino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.sprite.VolatileSprite;

//TODO: Vooga Sprites don't work because you can't add them to a Playfield.
//import vooga.sprites.improvedsprites.Sprite;
//import vooga.sprites.spritegroups.SpriteGroup;

public class TetrisGame extends Game implements ITetrisContext {
	private final ResourceManager resources = new ResourceManager(
			"games.lolcats.TetrisResource");
	private final ResourceManager keypressResource = new ResourceManager(
			"games.lolcats.KeypressResource");

	private Map<Integer, IKeyAction> keyMappings;

	private PlayField playfield;

	private SpriteGroup mazeGroup;
	private SpriteGroup tetrominoGroup;
	private SpriteGroup destinationGroup;

	private Sprite matrixBackground;

	private GameFont font;

	private Tetromino activeMino;

	private boolean gameOver;
	private Sprite maze;

	private static boolean musicIsPlaying = false;

	/****************************************************************************/
	/**************************** GAME SKELETON *********************************/
	/****************************************************************************/

	@Override
	public void initResources() {
		this.initGameEssentials();
		this.initMusic();
		this.initGameTimers();
		this.initKeyMappings();
		this.initSpriteComponents();
		gameOver = false;
	}

	public void reset() {
		initResources();
	}

	private void initGameEssentials() {
		playfield = new PlayField();
		Background windowBackground = new ColorBackground(Color.pink, resources
				.getInteger("BackgroundWidth"), resources
				.getInteger("BackgroundHeight"));
		playfield.setBackground(windowBackground);
		font = fontManager.getFont(getImages(resources.getString("FontImage"),
				20, 3), resources.getString("FontMapping"));
	}

	private void initGameTimers() {
	}

	private void initMusic() {
		if (!musicIsPlaying) {
			bsSound
					.setBaseRenderer(new com.golden.gamedev.engine.audio.WaveRenderer());
			bsSound.play(resources.getString("themeAudioURL"));
			musicIsPlaying = true;
		}
	}

	private void initKeyMappings() {
		keyMappings = new HashMap<Integer, IKeyAction>();
		for (String s : keypressResource.keySet()) {
			int key = 0;
			try {
				key = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				continue;
			}

			String className = keypressResource.getString(s);
			String packagePath = keypressResource.getString("PackageName");
			Class<?> c = null;
			try {
				c = Class.forName(packagePath + "." + className);
				Constructor<?> ctr = c.getConstructor();
				IKeyAction am = (IKeyAction) ctr.newInstance();
				keyMappings.put(key, am);
			} catch (Exception e) {
			}
		}
	}

	private void initSpriteComponents() {
		playfield.setComparator(new SpriteLayerComparator());
		mazeGroup = new SpriteGroup("maze");
		maze = new Sprite(
				getImage(resources.getString("MatrixBackgroundImage")));
		mazeGroup.add(maze);
		playfield.addGroup(mazeGroup);
		tetrominoGroup = new SpriteGroup("tetromino");
		activeMino = new Tetromino(this, getImage(resources
				.getString("tetrominoImageURL")));
		activeMino.setX(resources.getInteger("activeMinoStartX"));
		activeMino.setY(resources.getInteger("activeMinoStartY"));
		tetrominoGroup.add(activeMino);
		playfield.addGroup(tetrominoGroup);
		destinationGroup = new SpriteGroup("destination");
		playfield.addGroup(destinationGroup);
		PixelPerfectCollision enemyShipToPlayer = new PixelPerfectCollision() {
			@Override
			public void collided(Sprite s1, Sprite s2) {
				s2.setActive(false);
				VolatileSprite explosion = new VolatileSprite(getImages(
						resources.getString("explosionImageURL"), 4, 1), s2
						.getX(), s2.getY());
				playfield.add(explosion);
				playfield.removeGroup(tetrominoGroup);
			}
		};
		// BasicCollisionGroup enemyShipToPlayer = new BasicCollisionGroup() {
		// @Override
		// public void collided(Sprite s1, Sprite s2) {
		// s2.setActive(false);
		// VolatileSprite explosion = new VolatileSprite(getImages(
		// resources.getString("explosionImageURL"), 4, 1), s2
		// .getX(), s2.getY());
		// playfield.add(explosion);
		// // playfield.removeGroup(tetrominoGroup);
		// }
		// };
		// enemyShipToPlayer.pixelPerfectCollision = true;
		playfield.addCollisionGroup(mazeGroup, tetrominoGroup,
				enemyShipToPlayer);
	}

	private void checkKeyPresses(long elapsedTime) {
		for (Integer i : keyMappings.keySet()) {
			if (keyPressed(i)) {
				keyMappings.get(i).performKeyPressed(this, elapsedTime);
			}
			if (keyDown(i)) {
				keyMappings.get(i).performKeyDown(this, elapsedTime);
			}
		}
	}

	@Override
	public void update(long elapsedTime) {
		this.checkKeyPresses(elapsedTime);

		if (!gameOver) {
			playfield.update(elapsedTime);
		}
	}

	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
		// draw info text
		// font.drawString(g, "DELAYED AUTOSHIFT: " +
		// Integer.toString(dasDelay),
		// 10, 10);
		// font.drawString(g, "AUTO REPEAT RATE: " + Integer.toString(arrDelay),
		// 10, 30);
		// font.drawString(g, "NEXT", resources
		// .getInteger("MatrixSpawnCenterWidth") + 240, 90);
		// font.drawString(g, "HOLD", 20, 90);
		if (gameOver) {
			font.drawString(g, "Epic FAIL!. Press R to restart: ", 100, 250);
		}

	}

	@Override
	protected void notifyExit() {

	}

	@Override
	public Tetromino getActiveMino() {
		return activeMino;
	}

	/****************************************************************************/
	/***************************** START-POINT **********************************/
	/****************************************************************************/

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new TetrisGame(), new Dimension(600, 600), false);
		game.start();
	}

}