package games.bigfish.collisions;

import java.awt.geom.Point2D;

import games.bigfish.FishSprite;
import games.bigfish.PlayerFish;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
public class PlayerHitsFish extends BasicCollisionGroup<PlayerFish, FishSprite>{
	
	public PlayerHitsFish(){
		pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(PlayerFish player, FishSprite fish) {
			
		if(player.getSize() >= fish.getSize()){
			player.addToEaten();
			fish.setActive(false);
		}
		else{
			System.out.println("You lose, loser!");
			System.exit(0);
		}
	}

	
	 
}
