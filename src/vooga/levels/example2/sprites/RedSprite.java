package vooga.levels.example2.sprites;

import java.awt.Graphics2D;

import vooga.sprites.improvedsprites.Sprite;

public class RedSprite extends Sprite {

	private static final long serialVersionUID = 1L;
	private boolean fast;
	
	public RedSprite (int x, int y, boolean fast) {
		super(null, x, y);
		this.fast = fast;
	}
	
	public void update(long elapsedTime) {
		super.update(elapsedTime);
	}
	
	public void render(Graphics2D g){
		super.render(g);
	}

}
