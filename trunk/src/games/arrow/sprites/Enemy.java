package games.arrow.sprites;

import games.arrow.ArrowGame;
import games.breakout.Breakout;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class Enemy extends Sprite {

	private boolean amDead;
	
	@Override
	public void render(Graphics2D g, int x, int y) {
		super.render(g, x, y);
		this.getCollisionShape().render(g);
	}

	public Enemy(BufferedImage image, int x, int y) {
		super(image, x, y);
		this.height =10;
		this.width = 10;
		this.setHorizontalSpeed((Math.random()-.5)*0.1);
		setDead(false);
	}

	public void die() {
		setSpeed(0,.2);
		setDead(true);
	}

	public void setDead(boolean amDead) {
		this.amDead = amDead;
	}

	public boolean isDead() {
		return amDead;
	}
	

}
