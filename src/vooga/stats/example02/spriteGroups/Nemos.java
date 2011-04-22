package vooga.stats.example02.spriteGroups;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import vooga.stats.example02.characters.Nemo;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * A sprite group that manages behavior for nemo
 * @author Yin
 *
 */
public class Nemos extends SpriteGroup
{
	private static String GAME_RESOURCE = "game";
	private Dimension myDimension = new Dimension(800, 600);
	private int myNum;
	
	public Nemos(String name, int num, BufferedImage image) {
		super(name);
		myNum = num;
		for (int i=0; i<myNum; i++)
		{
			// Set nemo's initial position in screen center
			this.add(new Nemo(image,
					(double) myDimension.getWidth()/2,
					(double) myDimension.getHeight()/2));
		}
	}
	
	/**
	 * Update user control from keyboard before updating other information
	 * @param elapsedTime
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 */
	public void execute(long elapsedTime, boolean up, boolean down, boolean left, boolean right)
	{
		
		Nemo nemo = (Nemo) this.getActiveSprite();
		nemo.setDirection(up, down, left, right);
		
		super.update(elapsedTime);
	}
	
}
