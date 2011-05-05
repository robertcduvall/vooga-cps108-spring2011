package games.keen.sprites;

import games.keen.KeenGame;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.AnimatedSprite;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Zap extends AnimatedSprite {
	public static final double SPEED = 0.015;
	public Zap(KeenGame game, int x, int y) {
		super(game.getImageLoader().getAnimation("zap"), x, y);
		super.setAnimationFrame(0, 0);
	}

	public void setDirection(Direction d) {
		if(d == Direction.EAST) {
			super.setHorizontalSpeed(SPEED);
		} else if(d == Direction.WEST) {
			super.setHorizontalSpeed(-SPEED);
		}
	}
}
