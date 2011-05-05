package games.keen.sprites;

import games.keen.KeenGame;

import java.awt.Graphics2D;

import vooga.sprites.improvedsprites.AnimatedSprite;

@SuppressWarnings("serial")
public class LevelSprite extends AnimatedSprite {
	KeenGame game;
	
	public LevelSprite(KeenGame game) {
		this.game = game;
	}
	
	public LevelSprite(KeenGame game, int x, int y) {
		super(x, y);
		this.game = game;
	}
	
	@Override
	public void render(Graphics2D g) {
		render(g, (int)getX() + game.getOffsetX(), (int)getY() + game.getOffsetY());
	}
}
