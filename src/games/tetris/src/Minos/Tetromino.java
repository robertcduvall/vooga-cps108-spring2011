package games.tetris.src.Minos;

import games.tetris.src.Context.ITetrisContext;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

public class Tetromino extends Sprite {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4913020634810530622L;
	private final double speed = 0.02;

	public Tetromino(ITetrisContext tc, BufferedImage image) {
		super(image);
	}

	public static final double LEFT = Math.PI;
	public static final double RIGHT = 0;
	public static final double UP = Math.PI * 3D / 2D;
	public static final double DOWN = Math.PI / 2D;

	public void setDirection(double angle) {
		this.setVerticalSpeed(Math.sin(angle) * speed);
		this.setHorizontalSpeed(Math.cos(angle) * speed);
	}
}
