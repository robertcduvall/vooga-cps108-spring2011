package vooga.viewTeam.refactored;

import com.golden.gamedev.object.Sprite;

public abstract class NewSprite extends Sprite {
	

	/*The following methods belong inside of sprite*/
	public double getLeftCoordinate() {
		return this.getX();
	}

	public double getRightCoordinate() {
		return this.getX() + this.getWidth();
	}

	public double getTopCoordinate() {
		return this.getY();
	}

	public double getBottomCoordinate() {
		return this.getY() + this.getHeight();
	}

}
