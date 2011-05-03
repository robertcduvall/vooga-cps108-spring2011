package vooga.network.example.plantvszombie.zombies;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.image.BufferedImage;

public class BucketHeadZombie extends Zombie
{

	public BucketHeadZombie(int x, int y,int row, PlantVsZombie game)
	{
		super(game.getImage("resources/zombie1.png"), x, y, row, game);
		life = 20;
		speed = -0.03;
		setHorizontalSpeed(speed);
	}

}
