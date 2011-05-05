package games.blasterMan;

import games.blasterMan.sprites.PlayerType;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.sprites.improvedsprites.Sprite;


@SuppressWarnings("serial")
public class FireBall extends Skill{
	public static final int myID = 1; 
	public FireBall() {
		super(myID);
	}
	@Override
	public void invoke(PlayerType player) {
		Sprite fireball = new Sprite();
		try {
			fireball = new Sprite(ImageIO.read(new File("src/games/blasterMan/resources/images/fireball")), 
					player.getX(), player.getY());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		fireball.setSpeed(.3*player.getDirection(), 0.0);
	}
	@Override
	public void upgrade() {
		castSpeed++;
	}
	
}
