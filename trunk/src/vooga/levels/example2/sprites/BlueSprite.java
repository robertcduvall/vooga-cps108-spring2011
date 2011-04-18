package vooga.levels.example2.sprites;

import java.awt.Graphics2D;

import vooga.sprites.improvedsprites.Sprite;

public class BlueSprite extends Sprite {

	private static final long serialVersionUID = 1L;
	private double speed;
	
	public BlueSprite (int x, int y, double speed) {
		super(null, x, y);
		this.speed = speed;
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}
	
}
