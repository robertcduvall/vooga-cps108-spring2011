package games.jumper.sprites;

import games.jumper.Jumper;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Block extends Sprite{
	
	public Block(BufferedImage image, int x, int y){
		super(image, 1 + x * (2 + image.getWidth()), 1 + y * (2 + image.getHeight()));
		this.setImage(Jumper.myImageLoader.getImage("block"));
		this.setAngle(Direction.NORTH.getAngle());
		this.setX(x);
		this.setY(y);
	}
}