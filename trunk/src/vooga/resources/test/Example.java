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

public class Example extends VoogaGame {

	private static final String RESOURCE_XML_FILE = "src/vooga/resources/test/test2.xml";
	private ImageLoader loader;
	private PlayField playfield;
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	
	@Override
	public void initResources() {
		loader = new ImageLoader(new File(RESOURCE_XML_FILE), bsLoader);
		playfield = new PlayField();
		BufferedImage ship = loader.getImage("ship");
		BufferedImage bg = loader.getImage("space");
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
