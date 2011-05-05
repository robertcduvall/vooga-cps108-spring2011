package games.keen;

import games.keen.sprites.Keen;

import java.awt.Dimension;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

public class KeenGame extends VoogaGame {
	private Keen player;
	private int offsetX;
	private int offsetY;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	@Override
	public void updatePlayField(long elapsedTime) {
		if(!player.isDead()) {
			offsetX = -(int)Math.max(0, player.getX()-WIDTH/2);
			offsetY = -(int)Math.max(0, player.getY()-HEIGHT/2);
		}
	}

	@Override
	public void initResources() {
		offsetX = 0;
		offsetY = 0;
		super.getLevelManager().loadLevel(0);
		for(Sprite sprite : super.getLevelManager().getCurrentLevel().getSpriteGroup("monsters").
				getSprites()) {
			if(sprite instanceof Keen) {
				player = (Keen) sprite;
				break;
			}
		}
	}
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public static void main(String[] args) {
        launchGame(new KeenGame(), new Dimension(WIDTH, HEIGHT), false);
	}

}
