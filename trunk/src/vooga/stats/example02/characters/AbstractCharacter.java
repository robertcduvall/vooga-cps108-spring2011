package vooga.stats.example02.characters;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import vooga.stats.example02.util.commands.ImageReader;

/**
 * An abstract class that represents characters in the game
 * such as players, enermeis..
 * @author Yin
 *
 */
public abstract class AbstractCharacter extends Sprite 
{	
	public AbstractCharacter(BufferedImage image)
	{
		super(image);
		
	}
}
