package games.bigfish.collisions;

import games.bigfish.FishSprite;
import games.bigfish.PlayerFish;
import games.breakout.sprites.Ball;
import games.breakout.sprites.Block;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.collisionManager.CollisionManager;
import vooga.collisions.intersections.IntersectionFactory;

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

	 @Override
	    public boolean areCollide(PlayerFish s1, FishSprite s2)
	 {
		 if (collider(s1,s2))
			 return CollisionManager.isPixelCollide(s1.getX(), s1.getY(), s1
                 .getImage(), s2.getX(), s2.getY(), s2.getImage());
		 return false;

	    }
	 private boolean collider(PlayerFish player, FishSprite enemy){
		 double dx = Math.abs(player.getCenterX() - enemy.getCenterX()) - enemy.getWidth()/2.0;
	        double dy = Math.abs(player.getCenterY() - enemy.getCenterY()) - enemy.getHeight()/2.0;
	        
	        if (dx > player.getWidth() || dy > player.getHeight())
	            return false;
	        
	        if (dx < 0 || dy < 0)
	            return true;
	        
	        return dx*dx + dy*dy < player.getWidth()*player.getWidth()+ player.getHeight() * player.getHeight();
	 }
}
