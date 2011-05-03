package vooga.network.example.plantvszombie.plants;

import vooga.network.example.plantvszombie.game.PlantVsZombie;
import vooga.network.example.plantvszombie.projectiles.BasicProjectile;
import vooga.network.example.plantvszombie.projectiles.FireProjectile;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

public class FirePeaShooter extends Plant
{
	private final String projectilePicture = "resources/projectile1.png";
	private Timer myTimer;
	
	public FirePeaShooter(int x, int y, int row, int column, PlantVsZombie game)
	{
		super(game.getImage("resources/plant3.png"), x, y, row,column, game);
		myTimer = new Timer(2000);
		life = game.getFPS()*4;
	}
	
	@Override
	public void update(long elapsedTime){
		if (myTimer.action(elapsedTime) && (game.ZOMBIE_GROUP[myRow].getActiveSprite()!=null)){
			Sprite tmp = new FireProjectile(game.getImage(projectilePicture),(int)this.getX()+10,(int)this.getY()+30);
			tmp.setHorizontalSpeed(0.3);
			game.PROJECTILE_GROUP[myRow].add(tmp);
		}
	}
}
