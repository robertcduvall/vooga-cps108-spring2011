package games.jumper.sprites;

import java.awt.image.BufferedImage;

import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Block extends Sprite{
	
	public Block(BufferedImage image, int x, int y){
		this.setImage(image);
		this.setX(x);
		this.setY(y);
	}
}