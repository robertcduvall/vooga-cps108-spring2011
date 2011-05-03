package vooga.network.example.plantvszombie.collisions;
import vooga.network.example.plantvszombie.game.PlantVsZombie;
import vooga.network.example.plantvszombie.zombies.*;
import vooga.network.example.plantvszombie.projectiles.*;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class ProjectileEnemyCollision extends BasicCollisionGroup
{
	PlantVsZombie game;
	
	public ProjectileEnemyCollision(PlantVsZombie game){
		this.game = game;
	}

	@Override
	public void collided(Sprite s0, Sprite s1)
	{
		((Zombie)s1).getDamage(((Projectile)s0).getPower());
        s0.setActive(false);
	}

}
