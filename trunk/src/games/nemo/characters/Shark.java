package games.nemo.characters;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.golden.gamedev.engine.timer.SystemTimer;

import games.nemo.util.resources.ResourceManager;

/**
 * This class manages behaviors for sharks
 * @author Yin
 *
 */
public class Shark extends AbstractCharacter
{
	
	private Random myRandom;
	private int mySpeed;
	
	private final Dimension myDimension = new Dimension(800,600); //
	
	public Shark(BufferedImage image, int speed)
	{
		super(image);
		mySpeed = speed;
		myRandom = new Random();
		setX(0);
		setY((double) myRandom.nextDouble()*myDimension.height);
	}
	
	/**
	 * Update shark's position randomly
	 */
	@Override
	public void update(long elapsedTime)
	{
		int direction = myRandom.nextInt()%8;
		
		if (direction==0)
		{
			setX((double) getX()+myRandom.nextDouble()*mySpeed);
			setY((double) getY()+myRandom.nextDouble()*mySpeed);
		}
		
		if (direction==1)
		{
			setX((double) getX()+myRandom.nextDouble()*mySpeed);
			setY((double) getY()-myRandom.nextDouble()*mySpeed);
			
		}
		
		if (direction==2)
		{
			setX((double) getX()-myRandom.nextDouble()*mySpeed);
			setY((double) getY()+myRandom.nextDouble()*mySpeed);
		}
		
		if (direction==3)
		{
			setX((double) getX()-myRandom.nextDouble()*mySpeed);
			setY((double) getY()-myRandom.nextDouble()*mySpeed);
		}
		
		if (direction==4)
			setX((double) getX()+myRandom.nextDouble()*mySpeed);
			
		if (direction==5)
			setY((double) getY()+myRandom.nextDouble()*mySpeed);
		
		if (direction==6)
			setX((double) getX()-myRandom.nextDouble()*mySpeed);
		
		if (direction==7)
			setY((double) getY()-myRandom.nextDouble()*mySpeed);
		
		super.update(elapsedTime);
	}
	
}
