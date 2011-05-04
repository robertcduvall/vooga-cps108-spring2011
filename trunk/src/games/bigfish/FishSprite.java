package games.bigfish;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.core.VoogaGame;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

@SuppressWarnings("serial")
public class FishSprite extends Sprite{

	private VoogaGame myGame;
	protected int mySize;
	
	public FishSprite(BufferedImage image, int x, int y){
		super(image,x,y);
		

	}
	

}
