package games.frogger.collisions;

import games.frogger.Frogger;
import games.frogger.sprites.Vehicle;
import games.frogger.sprites.components.SpriteTypeC;
import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;

public class VehicleEdgeCollision extends EdgeCollisionGroup<Vehicle> {
	
	@Override
	public void collidedTop(Vehicle s) {
		s.setActive(false);
		Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy", s.getClass());
	}

	@Override
	public void collidedRight(Vehicle s) {
		if(s.getOldX() - s.getWidth()/2 >= 550 ){
			s.setActive(false);
			Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy", s.getComponent(SpriteTypeC.class).getString());
		}
	}

	@Override
	public void collidedLeft(Vehicle s) {
		if(s.getOldX() < 0 - s.getHeight()){
			s.setActive(false);
			Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy", s.getComponent(SpriteTypeC.class).getString());
		}
	}

	@Override
	public void collidedBottom(Vehicle s) {
		s.setActive(false);
		Frogger.myEventManager.fireEvent(this, "Vehicle.Destroy");
	}

}
