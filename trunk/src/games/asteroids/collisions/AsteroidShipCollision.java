package games.asteroids.collisions;

import games.asteroids.Asteroid;
import games.asteroids.Ship;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;


public class AsteroidShipCollision extends BasicCollisionGroup<Asteroid, Ship>
{

    public AsteroidShipCollision() {
		super();
	}

	public AsteroidShipCollision(SpriteGroup<Asteroid> s1, SpriteGroup<Ship> s2) {
		super(s1, s2);
	}

	@Override
	public void collided(Asteroid s1, Ship s2) {
		
	}




}
