package games.missileDefense.sprites;

import vooga.core.VoogaGame;
import vooga.sprites.improvedsprites.Sprite;
import vooga.util.buildable.components.predefined.basic.HealthC;

public class Gun extends Sprite
{
	private VoogaGame game;
	
	public Gun(int x, int y, double health, VoogaGame game)
	{
		super(game.getImage("gun"));

		this.game = game;
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
