package vooga.network.example.plantvszombie.plants;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

import java.awt.image.BufferedImage;

import vooga.network.example.plantvszombie.projectiles.BasicProjectile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class SunFlower extends Plant
{
	Timer myTimer;
	public SunFlower(int x, int y, int row,int column, PlantVsZombie game)
	{
		super(game.getImage("resources/plant1.png"), x, y,row,column, game);
		life = game.getFPS()*4;
		this.game = game;
		myTimer = new Timer(7500);
	}
	
	@Override
	public void update(long elapsedTime){
		if (myTimer.action(elapsedTime)){
			game.addSun(50);
		}
	}
}
