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
		
		
		int direction = (int)(Math.random()*4) % 4;
		setRandomLocation();
		/*
		if (direction==0)
			this.setLocation(Math.random()*myDimension.width, 0);
		
		if (direction==1)
			this.setLocation(myDimension.width, 
					Math.random()*myDimension.height);
		
		if (direction==2)
			this.setLocation(Math.random()*myDimension.width,
					myDimension.height);
		
		if (direction==3)
			this.setLocation(0, Math.random()*myDimension.height);
			*/
		
		double actualSpeed = (double) mySpeed/50;
		double xSpeed = Math.random() * actualSpeed;
		double ySpeed = Math.sqrt(Math.pow(actualSpeed,2) - Math.pow(xSpeed, 2));
		if (direction == 2)
			xSpeed *= -1;
		else if (direction == 3)
			ySpeed *= -1;
		this.setSpeed(xSpeed, ySpeed);
		
	}
	
	/**
	 * Update shark's position randomly
	 */
	@Override
	public void update(long elapsedTime)
	{
		
		
		//int direction = myRandom.nextInt()%8;
		
		/*
		int direction = (int) (Math.random()*4)%4;
		
		if (direction==0)
			setLocation(getX(),getY()+Math.random());
		if (direction==1)
			setLocation(getX()+Math.random(), getY());
		if (direction==2)
			setLocation(getX(), getY()-Math.random());
		if (direction==3)
			setLocation(getX()-Math.random(), getY());
			*/
		
		/*
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
			*/
		
		checkSafe();
		super.update(elapsedTime);
	}
	
	private void setRandomLocation()
	{
		int direction = (int)(Math.random()*4) % 4;
		if (direction==0)
			this.setLocation(Math.random()*myDimension.width, 0);
		
		if (direction==1)
			this.setLocation(myDimension.width, 
					Math.random()*myDimension.height);
		
		if (direction==2)
			this.setLocation(Math.random()*myDimension.width,
					myDimension.height);
		
		if (direction==3)
			this.setLocation(0, Math.random()*myDimension.height);
	}
	
	private void checkSafe()
	{
		/*
		if (getX()<0) setX(0);
		if (getX()>myDimension.width) setX(myDimension.width);
		if (getY()<0) setY(0);
		if (getY()>myDimension.height) setY(myDimension.height);
			*/
		
		if (getX()<0 || getX()>myDimension.width 
				|| getY()<0 || getY()>myDimension.height)
			setRandomLocation();
			
	}
	
}
