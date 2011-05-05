package games.bigfish.collisions;

import games.bigfish.FishSprite;
import games.bigfish.PlayerFish;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class FishHitsWall extends EdgeCollisionGroup<FishSprite> {

	

	@Override
	public void collidedRight(FishSprite s) {
		if(!(s instanceof PlayerFish)){
			s.setActive(false);
		}	
	}

	@Override
	public void collidedBottom(FishSprite s) {
	}

	@Override
	public void collidedLeft(FishSprite s) {
	}

	@Override
	public void collidedTop(FishSprite s) {
	}

}
