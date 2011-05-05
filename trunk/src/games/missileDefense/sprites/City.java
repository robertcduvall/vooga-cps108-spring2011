package games.missileDefense.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.predefined.basic.HealthC;

public class City extends Sprite
{
	
	public City(BufferedImage image, int x, int y, double health)
	{
		super(image, x, y);
		this.addComponent(new HealthC(health));
	}
	
	public void damage()
	{
		this.getComponent(HealthC.class).decrease(1.0);
		if(getComponent(HealthC.class).isDead())
		{
			this.setActive(false);
		}
	}
}
