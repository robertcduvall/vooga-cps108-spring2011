package games.keen.sprites;

import java.awt.Graphics2D;

import vooga.sprites.improvedsprites.AnimatedSprite;

import games.keen.levels.Level;

public class Tile extends AnimatedSprite {
	protected Level level;
	protected boolean clipping;
	
	public Tile(Level level) {
		super();
		
		this.level = level;
		clipping = true;
	}
		
	protected boolean isClipping() {
		return clipping;
	}
	
	@Override
	public void render(Graphics2D g) {
		render(g, (int)getX() + level.getOffsetX(), (int)getY() + level.getOffsetY());
	}
}
