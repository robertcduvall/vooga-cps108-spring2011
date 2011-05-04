package games.arrow.sprites;

import games.arrow.ArrowGame;
import games.breakout.Breakout;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

public class Enemy extends Sprite {

	public Enemy(BufferedImage image, int x, int y) {
		super(image, x, y);
		this.height =100;
		this.width = 100;
//		this.setHorizontalSpeed((Math.random()-.5)*0.1);
		System.out.println("Hi? I'm an enemy!");
		System.out.println(image);
	}

}
