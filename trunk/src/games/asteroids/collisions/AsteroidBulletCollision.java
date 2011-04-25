package games.asteroids.collisions;

import games.asteroids.Asteroid;
import games.asteroids.Bullet;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.collisions.intersections.IntersectionFactory;
import vooga.collisions.intersections.PolygonPolygonFinder;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;


public class AsteroidBulletCollision extends BasicCollisionGroup<Asteroid, Bullet>
{


    public AsteroidBulletCollision() {
		super();
	}

	public AsteroidBulletCollision(SpriteGroup<Asteroid> s1,
			SpriteGroup<Bullet> s2) {
		super(s1, s2);
	}

	@Override
	public void collided(Asteroid s1, Bullet s2) {
		
		System.out.println("Collided!"+s1+" "+s2);
        s1.setActive(false);
        s2.setActive(false);
		
	}

	
	
}
