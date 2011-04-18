package games.invaders.game.ships;

import games.invaders.game.InvadersGame;

import java.awt.event.KeyEvent;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class Hero extends Ship {
	InvadersGame game;
	private final SpriteGroup missileGroup = new SpriteGroup(
			"Player Missle Group");
	boolean playerCanFire = true;
	Timer playerMissleTimer = new Timer(50);
	double equilibriumHeight;
	private double hitpoints = 3;

	public Hero(GameObject game) {
		super(game.getImage("pship.png"));
		this.game = (InvadersGame) game;
		equilibriumHeight = this.game.fieldDimensions.getSize().getHeight() - 110;
	}

	double activeMissile = 0;

	/**
	 * @return the missileGroup
	 */
	public SpriteGroup getMissileGroup() {
		return missileGroup;
	}

	@Override
	public void update(long elapsedTime) {
		super.update(elapsedTime);
		if (game.keyDown(KeyEvent.VK_LEFT)) {
			addHorizontalSpeed(game.unitMoveTimer.getDelay(), -0.0015, -5000);
		}
		if (game.keyDown(KeyEvent.VK_RIGHT)) {
			addHorizontalSpeed(game.unitMoveTimer.getDelay(), 0.0015, 5000);
		}
		// if (getX() < 5) {
		// setX(game.fieldDimensions.getWidth() - 5);
		// setSpeed(getHorizontalSpeed(), getVerticalSpeed());
		// }
		// if (getX() > game.fieldDimensions.getWidth() - 5) {
		// setX(5);
		// setSpeed(getHorizontalSpeed(), getVerticalSpeed());
		// }
		// TODO: For demo only, Restore this for springiness in actual game
		double verticalAcceleration = 0;
		verticalAcceleration += (equilibriumHeight - getY()) * 0.00004;
		if (game.keyDown(KeyEvent.VK_UP)) {
			verticalAcceleration -= 0.0006;
		}
		if (game.keyDown(KeyEvent.VK_DOWN)) {
			verticalAcceleration += 0.0006;
		}
		addVerticalSpeed(game.unitMoveTimer.getDelay(), verticalAcceleration,
				(verticalAcceleration > 0) ? 10 : -10);
		setVerticalSpeed(getVerticalSpeed()
				* Math.pow(2, -(elapsedTime) / 1000D));
		setHorizontalSpeed(getHorizontalSpeed()
				* Math.pow(2, -elapsedTime / 1000D));
		// Fire missile -- Uses "timer" to set fire rate
		// KeyEvent.
		if (playerCanFire && isActive()) {
			fireMissiles();
			// **** UPDATE FLAG FOR FIRING ****/
		} else {
			if (playerMissleTimer.action(elapsedTime)) {
				playerCanFire = true;
			}
		}
	}

	public void fireMissiles() {
		if (game.keyDown(KeyEvent.VK_SPACE)) {
			double missileDisplacement = 7;
			if (activeMissile == 0) {
				activeMissile = 1;
			} else {
				activeMissile = 0;
				missileDisplacement *= -1;
			}
			Sprite playerMissile = new Sprite(game.getImage("pmissle.png"));
			playerMissile.setLocation(getCenterX() + missileDisplacement,
					getY() - 15);
			playerMissile.setVerticalSpeed(-0.50);

			getMissileGroup().add(playerMissile);
			// **** DISABLE FLAG FOR FIRING ****//
			playerCanFire = false;
			playerMissleTimer.refresh();
		}
	}

	/**
	 * @return the hitpoints
	 */
	public double getHitpoints() {
		return hitpoints;
	}

	/**
	 * @param hitpoints
	 *            the hitpoints to set
	 */
	public void setHitpoints(double hitpoints) {
		this.hitpoints = hitpoints;
		if (this.hitpoints <= 0) {
			this.setActive(false);
		}
	}

	public void subtractHitpoints(double value) {
		this.hitpoints -= value;
		if (this.hitpoints <= 0) {
			this.setActive(false);
		}
	}

}
