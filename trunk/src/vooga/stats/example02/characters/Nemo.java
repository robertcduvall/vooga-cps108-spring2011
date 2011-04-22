package vooga.stats.example02.characters;

import java.awt.image.BufferedImage;

/**
 * This class managers the behavior for each player by keyboard
 * @author Yin
 *
 */
public class Nemo extends AbstractCharacter 
{

	private boolean myUp, myDown, myLeft, myRight;
	protected double myStep;
	protected double STEP = 1;
	
	public Nemo(BufferedImage image, double x, double y)
	{
		super(image);
		this.setX(x);
		this.setY(y);
		
		myUp = false; myDown = false;
		myLeft = false; myRight = false;
		
		myStep = STEP;
	}
	
	/**
	 * Set the direction information from keyboard
	 * @param up
	 * @param down
	 * @param left
	 * @param right
	 */
	public void setDirection(boolean up, boolean down, boolean left, boolean right)
	{
		myUp = up; myDown = down; myLeft = left; myRight = right;
	}
	
	/**
	 * Update nemo's position
	 */
	@Override
	public void update(long elapsedTime)
	{
		if (myUp)    moveY(-myStep);
		if (myDown)  moveY(myStep);
		if (myLeft)  moveX(-myStep);
		if (myRight) moveX(myStep);
		super.update(elapsedTime);
	}
	
}
