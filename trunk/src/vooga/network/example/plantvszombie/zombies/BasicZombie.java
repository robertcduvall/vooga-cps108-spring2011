package vooga.network.example.plantvszombie.zombies;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.image.BufferedImage;

public class BasicZombie extends Zombie
{
	public BasicZombie(int x, int y,int row, PlantVsZombie game)
	{
		super(game.getImage("resources/zombie0.png"), x, y,row, game);
		life = 10;
		speed = -0.03;
		setHorizontalSpeed(speed);
	}
}
