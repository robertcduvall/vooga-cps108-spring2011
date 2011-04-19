package games.invaders.game;

import games.invaders.game.level.Level;
import games.invaders.game.level.Level1;
import games.invaders.game.ships.Hero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import vooga.viewTeam.PlayFieldFrame;
import vooga.viewTeam.WrappingCollisionGroup;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ColorBackground;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;

public class InvadersGame extends GameObject {

	public PlayField playfield;

	public GameFont font2;

	public SpriteGroup enemyGroup = new SpriteGroup("Enemy formation");
	public List<Sprite> enemies;

	public VolatileSprite explosion;
	public Hero hero;

	public Timer endTimer = new Timer(3000);
	public Timer discoTimer = new Timer((int) Math.round((1000.0 * 60) / 118));

	public BasicCollisionGroup playerMissleToEnemy, enemyMissleToPlayer,
			enemyShipToPlayer;

	public long score = 0;
	public boolean endOfGame;

	public Dimension fieldDimensions = bsGraphics.getSize();

	public Level level;

	public InvadersGame(GameEngine parent) {
		super(parent);
	}

	/**
	 * TODO: A better way of doing levels for this game would be to have the
	 * current level to launch the next level, giving the level it creates it's
	 * creator, so as to maintain continuity of game state and not have to
	 * instantiate everything all the time.
	 */

	@Override
	public void initResources() {
		hideCursor();
		font2 = fontManager
				.getFont(getImage("resources/images/BitmapFont.png"));

		// background = new
		// ImageBackground(getImage("resources/images/back.png"), 320, 240);
		playfield = new PlayField(new ColorBackground(Color.BLACK));

		setMaskColor(Color.BLACK); // Makes sprite background transparent

		// Sprite Initialization
		// Create player hero
		hero = new Hero(this);
		// TODO: Why does hero need to be final, and is this bad?
		hero.setLocation(fieldDimensions.getWidth() / 2, fieldDimensions
				.getHeight() - 50);
		enemyGroup = new SpriteGroup("Enemy Group");
		List<SpriteGroup> addToFormations = new ArrayList<SpriteGroup>();
		addToFormations.add(enemyGroup);
		level = new Level1(this);

		// Create hero group
		SpriteGroup playerGroup = new SpriteGroup("Player Group");
		playerGroup.add(hero);
		// Missiles added to these groups at fire time
		SpriteGroup playerMissileGroup = hero.getMissileGroup();
		SpriteGroup enemyMissileGroup = new SpriteGroup("Enemy Missle Group");
		// Sprite Management #3 //
		playfield.addGroup(playerGroup);
		playfield.addGroup(playerMissileGroup);
		playfield.addGroup(enemyGroup);
		playfield.addGroup(enemyMissileGroup);
		// Collision Detection
		// Player missle collision with enemy ships
		playerMissleToEnemy = new BasicCollisionGroup() {
			@Override
			public void collided(Sprite s1, Sprite s2) {
				s1.setActive(false);
				s2.setActive(false);
				explosion = new VolatileSprite(getImages(
						"resources/images/Explode.png", 4, 1), s2.getX(), s2
						.getY());
				playfield.add(explosion);
				score += 10; // Reward the player :)
			}
		};
		playfield.addCollisionGroup(playerMissileGroup, enemyGroup,
				playerMissleToEnemy);
		// Enemy missile collision with players ship
		enemyMissleToPlayer = new BasicCollisionGroup() {
			@Override
			public void collided(Sprite s1, Sprite s2) {
				s1.setActive(false);
				s2.setActive(false);
				explosion = new VolatileSprite(getImages(
						"resources/images/ExplodeRed.png", 4, 1), s2.getX(), s2
						.getY());
				playfield.add(explosion);
				hero.subtractHitpoints(1);
			}
		};
		playfield.addCollisionGroup(enemyMissileGroup, playerGroup,
				enemyMissleToPlayer);
		// Enemy Ship collision with players ship
		enemyShipToPlayer = new BasicCollisionGroup() {
			@Override
			public void collided(Sprite s1, Sprite s2) {
				s1.setActive(false);
				explosion = new VolatileSprite(getImages(
						"resources/images/ExplodeRed.png", 4, 1), s2.getX(), s2
						.getY());
				playfield.add(explosion);
				score += 10;
				hero.subtractHitpoints(1);
			}
		};
		playfield.addCollisionGroup(enemyGroup, playerGroup, enemyShipToPlayer);
		level.loadLevel();

		WrappingCollisionGroup wrapCollisionGroup = new WrappingCollisionGroup();
		wrapCollisionGroup.setWrapVertical(false);
		Sprite playFieldFrame = new PlayFieldFrame(playfield);
		SpriteGroup edgeGroup = new SpriteGroup("Background edge");
		edgeGroup.add(playFieldFrame);
		SpriteGroup wrapGroup = new SpriteGroup("Wrap group");
		wrapGroup.add(hero);
		playfield.addCollisionGroup(wrapGroup, edgeGroup, wrapCollisionGroup);
		hero.setX(50);

	}

	@Override
	public void update(long elapsedTime) {
		// TODO: Break method up into parts
		playfield.update(elapsedTime);
		updateBackground(elapsedTime);
		// TODO: Make enemies fire
		// Press escape key to exit level
		if (keyDown(KeyEvent.VK_ESCAPE)) {
			parent.nextGameID = Invaders.TITLE;
			finish();
		}
		if (endOfGame) {
			if (endTimer.action(elapsedTime)) {
				parent.nextGameID = Invaders.TITLE;
				finish();
			}
		}
		if (keyPressed(KeyEvent.VK_BACK_SLASH)) {
			level.setLevelCompleted(true);
		}
		if (level.isComplete()) {
			hero.setX(50);
			level = level.loadNext();
			hero.setHitpoints(2);
		}
		if (!hero.isActive()) {
			setEndOfGame(true);
		}

	}

	private void updateBackground(long elapsedTime) {
		if (discoTimer.action(elapsedTime)) {
			// Background colorBackground = new ColorBackground(new Color(0));
			Background colorBackground = new ColorBackground(new Color(
					(int) (Math.random() * 256 * 256 * 256)));
			playfield.setBackground(colorBackground);
			discoTimer.refresh();
		}
	}

	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
		// font2.drawString(g, "WAVE 1", 110, 240 / 2);
		// TODO: Display wave/level information
		font2.drawString(g, "SCORE: " + String.valueOf(score), 1, 12);
		g.setColor(Color.YELLOW);
		g.drawString("Press ESCAPE to quit", 150, 22);
	}

	/**
	 * @return the endOfGame
	 */
	public boolean isEndOfGame() {
		return endOfGame;
	}

	/**
	 * @param endOfGame
	 *            the endOfGame to set
	 */
	public void setEndOfGame(boolean endOfGame) {
		this.endOfGame = endOfGame;
	}

}
