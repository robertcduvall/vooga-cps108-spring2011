package games.jumper.sprites;

import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Spikes extends Sprite{
	

	public Spikes(BufferedImage image, int x, int y){
		this.setImage(image);
		this.setX(x);
		this.setY(y);
		this.setAngle(Direction.NORTH.getAngle());
	}
}