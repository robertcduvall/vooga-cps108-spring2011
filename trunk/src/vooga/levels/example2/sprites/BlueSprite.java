package vooga.levels.example2.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class BlueSprite extends Sprite {

	private static final long serialVersionUID = 1L;
	private double mySpeed;
	
	public BlueSprite (BufferedImage img, int x, int y) {
		super(img, x, y);
		mySpeed = 5;
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}
	
}
