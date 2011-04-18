package games.invaders.game.ships;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Ship extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2089505402519969509L;
	protected double rotation;
	protected double hitPoints;

	public Ship(BufferedImage image, int x, int y) {
		super(image, x, y);
	}

	public Ship(BufferedImage image) {
		super(image);
	}

	public Ship(int x, int y) {
		super(null, x, y);
	}

	public Ship() {
		super();
	}

	/**
	 * @return the rotation (in radians)
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * @param rotation
	 *            the rotation to set (in radians)
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
		if (rotation < 0) {
			rotation = ((rotation % (2 * Math.PI)) + 1) * (2 * Math.PI)
					+ rotation;
		}
		if (rotation > 2 * Math.PI) {
			rotation = rotation % (2 * Math.PI);
		}
	}

	/**
	 * @return the hitPoints
	 */
	public double getHitPoints() {
		return hitPoints;
	}

	/**
	 * @param hitPoints
	 *            the hitPoints to set
	 */
	public void setHitPoints(double hitPoints) {
		this.hitPoints = hitPoints;
	}

}
