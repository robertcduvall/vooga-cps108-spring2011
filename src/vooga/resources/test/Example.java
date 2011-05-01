package vooga.resources.test;

import java.awt.Dimension;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.core.VoogaGame;
import vooga.resources.images.*;

/**
 * Example program to demonstrate resource loading.
 * @author Sterling Dorminey
 *
 */
public class Example extends VoogaGame {

	private static final String RESOURCE_XML_FILE =
		"src/vooga/resources/test/example_main_rsrc.xml";
	private PlayField playfield;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	public Example() {
	}
	
	@Override
	public void initResources() {
		playfield = new PlayField();
		BufferedImage ship = getImageLoader().getImage("ship");
		BufferedImage bg = getImageLoader().getImage("space");
		playfield.setBackground(new ImageBackground(bg));
		Sprite testSprite = new Sprite(ship, WIDTH/2, HEIGHT/2);
		playfield.add(testSprite);
	}

	@Override
	public void render(Graphics2D g) {
		playfield.render(g);
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		playfield.update(elapsedTime);
	}

    public static void main (String[] args)
    {
        GameLoader game = new GameLoader();
        game.setup(new Example(), new Dimension(WIDTH,HEIGHT), false);
        game.start();
    }
}
