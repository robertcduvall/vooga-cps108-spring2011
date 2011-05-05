package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerPaddle extends AbstractPaddle{

	public PlayerPaddle(VoogaGame game, double x, double y) {
		super(game.getImageLoader().getImage("paddle"), x, y);
	}
	
	

}
