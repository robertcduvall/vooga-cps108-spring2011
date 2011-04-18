package view;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class WrappingCollisionGroup extends CollisionGroup {
	private boolean wrapHorizontal = true;
	private boolean wrapVertical = true;

	@Override
	public void collided(Sprite target, Sprite wrapInsideOf) {
		if (wrapHorizontal) {
			if (getLeftCoordinate(target) < getLeftCoordinate(wrapInsideOf)) {
				target.setX(getRightCoordinate(wrapInsideOf)
						- target.getWidth());
			} else if (getRightCoordinate(target) > getRightCoordinate(wrapInsideOf)) {
				target.setX(getLeftCoordinate(wrapInsideOf));
			}
		}
		if (wrapVertical) {
			if (getTopCoordinate(target) < getTopCoordinate(wrapInsideOf)) {
				target.setY(getBottomCoordinate(wrapInsideOf)
						- target.getHeight());
			} else if (getBottomCoordinate(target) > getBottomCoordinate(wrapInsideOf)) {
				target.setY(getTopCoordinate(wrapInsideOf));
			}
		}
	}

	// CollisionBounds detects backgrounnd as edge group

	/**
	 * @return the wrapHorizontal
	 */
	public boolean isWrapHorizontal() {
		return wrapHorizontal;
	}

	/**
	 * @param wrapHorizontal
	 *            the wrapHorizontal to set
	 */
	public void setWrapHorizontal(boolean wrapHorizontal) {
		this.wrapHorizontal = wrapHorizontal;
	}

	/**
	 * @return the wrapVertical
	 */
	public boolean isWrapVertical() {
		return wrapVertical;
	}

	/**
	 * @param wrapVertical
	 *            the wrapVertical to set
	 */
	public void setWrapVertical(boolean wrapVertical) {
		this.wrapVertical = wrapVertical;
	}

	private double getLeftCoordinate(Sprite sprite) {
		return sprite.getX();
	}

	private double getRightCoordinate(Sprite sprite) {
		return sprite.getX() + sprite.getWidth();
	}

	private double getTopCoordinate(Sprite sprite) {
		return sprite.getY();
	}

	private double getBottomCoordinate(Sprite sprite) {
		return sprite.getY() + sprite.getHeight();
	}

}
