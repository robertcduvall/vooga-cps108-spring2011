package games.nemo.spriteGroups;

import java.awt.image.BufferedImage;
import java.util.Random;

import games.nemo.util.commands.ImageReader;

import games.nemo.characters.Shark;

import com.golden.gamedev.object.SpriteGroup;

/**
 * A sprite group that manages behaviors for sharks
 * @author Yin
 *
 */
public class Sharks extends SpriteGroup 
{
	
	public Sharks(String name, int num, BufferedImage image, int speed) {
		super(name);
		
		// Add given amount of sharks
		for (int i=0; i<num; i++)
		{
			this.add(new Shark(image, speed));
		}
		this.setActive(true);
	}
}
