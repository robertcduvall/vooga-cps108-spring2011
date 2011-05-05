package games.jumper.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class Spikes extends Sprite{
	
	@Override
    public void render(Graphics2D g) {
            super.render(g);
            this.getCollisionShape().render(g);
    }
	public Spikes(BufferedImage image, int x, int y){
		this.setImage(image);
		this.setX(x);
		this.setY(y);
		this.setAngle(Direction.NORTH.getAngle());
	}
}