package games.keen.collisions;

import games.keen.sprites.Keen;
import games.keen.sprites.MonsterSprite;
import games.keen.sprites.StaticTile;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.resources.Direction;

public class TileCollision extends BasicCollisionGroup<MonsterSprite, StaticTile> {

	public TileCollision() {
		super.pixelPerfectCollision = true;
	}
	
	@Override
	public void collided(MonsterSprite s1, StaticTile s2) {
		s1.handleCollision();
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