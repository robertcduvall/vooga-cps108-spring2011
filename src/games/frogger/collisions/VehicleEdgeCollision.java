package games.frogger.collisions;

import games.frogger.Frogger;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

public class VehicleEdgeCollision extends EdgeCollisionGroup<Sprite> {
	
	@Override
	public void collidedTop(Sprite s) {
		s.setActive(false);
		Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy");
		System.out.println("top");
	}

	@Override
	public void collidedRight(Sprite s) {
		System.out.println("right");
		//s.setActive(false);
		//Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy");
		
	}

	@Override
	public void collidedLeft(Sprite s) {
		s.setActive(false);
		Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy");
		System.out.println("left");
	}

	@Override
	public void collidedBottom(Sprite s) {
		s.setActive(false);
		Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy");
		System.out.println("bottom");
	}

}
