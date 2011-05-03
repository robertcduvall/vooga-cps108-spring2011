package vooga.network.example.plantvszombie.plants;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import vooga.network.example.plantvszombie.projectiles.BasicProjectile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import vooga.network.example.plantvszombie.game.PlantVsZombie;

public class PeaShooter extends Plant
{
	private final String projectilePicture = "resources/projectile0.png";
	private Timer myTimer;
	
	
	public PeaShooter(int x, int y, int row, int column, PlantVsZombie game)
	{
		super(game.getImage("resources/plant0.png"), x, y, row,column, game);
		myTimer = new Timer(2000);
		// 4 seconds
		life = game.getFPS()*4;
	}
	
	@Override
	public void update(long elapsedTime){
		if (myTimer.action(elapsedTime) && (game.ZOMBIE_GROUP[myRow].getActiveSprite()!=null)){
			Sprite tmp = new BasicProjectile(game.getImage(projectilePicture),(int)this.getX()+10,(int)this.getY()+30);
			tmp.setHorizontalSpeed(0.3);
			game.PROJECTILE_GROUP[myRow].add(tmp);
		}
	}
}