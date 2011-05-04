package games.bigfish.collisions;

import games.bigfish.FishSprite;
import games.bigfish.PlayerFish;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class PlayerHitsFish extends BasicCollisionGroup<PlayerFish, FishSprite>{

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
