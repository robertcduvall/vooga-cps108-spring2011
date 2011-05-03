package vooga.network.example.plantvszombie.collisions;

import vooga.network.example.plantvszombie.game.PlantVsZombie;
import vooga.network.example.plantvszombie.plants.Plant;
import vooga.network.example.plantvszombie.zombies.Zombie;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

public class EnemyPlantCollision extends CollisionGroup
{
	PlantVsZombie game;
	
	public EnemyPlantCollision(PlantVsZombie game){
		this.game = game;
	}
	
	@Override
	public void collided(Sprite s0, Sprite s1)
	{
		revertPosition1();
		((Plant)s1).getDamage(((Zombie)s0).getPower());
	}

}
