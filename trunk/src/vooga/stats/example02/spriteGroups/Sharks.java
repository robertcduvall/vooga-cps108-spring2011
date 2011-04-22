package vooga.stats.example02.spriteGroups;

import java.awt.image.BufferedImage;
import java.util.Random;

import vooga.stats.example02.util.commands.ImageReader;

import vooga.stats.example02.characters.Shark;

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
