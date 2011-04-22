package vooga.levels.example2.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class RedSprite extends Sprite {

	private static final long serialVersionUID = 1L;
	private boolean myFast;
	
	public RedSprite (BufferedImage img, int x, int y) {
		super(img, x, y);
		myFast = true;
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}

}
