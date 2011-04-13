package vooga.replay.examples.catroll;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.util.ImageUtil;

public class DogSprite extends Sprite {
	
	private Random gen = new Random();
	int angle = 0;
	private BufferedImage image;
	
	public DogSprite(BufferedImage image){
		super(image);
		this.image = image;
	}
	
	@Override
	public void render(Graphics2D g){
		super.render(g);
		
	}
	
	@Override
	public void update(long elapsedTime){
		int randomness = (int)(gen.nextDouble()*50)-25;
		this.move(0.005 * elapsedTime, 0.0005 * elapsedTime * randomness );
		angle += elapsedTime;
		this.setImage(ImageUtil.rotate(image, angle));
		System.out.println(this.getX() + " ~ " + this.getY());
		//super.update(elapsedTime);
	}

}
