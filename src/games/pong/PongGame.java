package games.pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.background.ColorBackground;

public class PongGame extends VoogaGame {

	PlayField playField;
	String SPRITE_LOCATION = "src/games/pong/resources/images/rectangle.png";

	public static void main(String[] args) {
		launchGame(new PongGame(), new Dimension(640, 480), false);
	}

	@Override
	public void render(Graphics2D g) {
		playField.render(g);
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		playField.update(elapsedTime);
	}

	@Override
	public void initResources() {
		Background background = new ColorBackground(Color.BLACK);
		BufferedImage paddle_image = null;
		try {
			paddle_image = ImageIO.read(new File(SPRITE_LOCATION));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sprite paddle1 = new Sprite(paddle_image);
		playField = new PlayField(background);
	}

}
