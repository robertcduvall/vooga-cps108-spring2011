package view;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;

public class BackgroundFrame extends Sprite {
	private final Background background;

	public BackgroundFrame(Background background) {
		super(background.getX(), background.getY());
		this.background = background;
	}

	@Override
	public double getX() {
		return background.getX();
	}

	@Override
	public double getY() {
		return background.getY();
	}

	@Override
	public int getHeight() {
		return background.getHeight();
	}

	@Override
	public int getWidth() {
		return background.getWidth();
	}

	/**
	 * @return the background
	 */
	@Override
	public Background getBackground() {
		return background;
	}

}
