package games.nemo.characters;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import games.nemo.util.commands.ImageReader;

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
