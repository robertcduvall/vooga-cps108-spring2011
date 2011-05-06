package games.jumpman.collisions;

import games.jumpman.sprites.Player;
import games.jumpman.sprites.Surface;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.resources.Direction;

public class SurfacePlayerCollision extends BasicCollisionGroup<Player, Surface> {

	public SurfacePlayerCollision() {
		super.pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(Player s1, Surface s2) {
		if(s1.getY()+s1.getHeight()-30<=s2.getY()+5){
			s1.hitSurface();
		}
	}
	
}
/*
public class TileCollision extends BasicCollisionGroup<Keen, StaticTile> {
	
	private static final int NUDGE = 2;
	
	@Override
	public void collided(Keen s1, StaticTile s2) {
		Direction d = null;
		// Test to see which side is collided with.
		double angle = super.getIntersectionAngle(s1, s2);
		if(angle < -Math.PI/4) {
			d = Direction.SOUTH;
		}
		else if(-Math.PI/4 < angle && angle < 0) {
			d = Direction.WEST;
		}
		else if(0 < angle && angle < Math.PI/4) {
			d = Direction.NORTH;
		} else {
			d = Direction.EAST;
		}
		
		if(d == Direction.SOUTH) {
			s1.moveY(-NUDGE);
		}
		if(d == Direction.WEST) {
			s1.moveX(NUDGE);
		}
		if(d == Direction.NORTH) {
			s1.moveY(NUDGE);
		}
		if(d == Direction.EAST) {
			s1.moveX(-NUDGE);
		}
		System.out.println(angle);
	}

}
*/