package games.missileDefense.collisions;

import games.missileDefense.sprites.Bullet;
import games.missileDefense.sprites.City;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.spritegroups.SpriteGroup;
/**
 * 
 * @author johnegan
 * This little diddy is what blows up cities
 *
 */
public class BulletCityCollision extends BasicCollisionGroup<Bullet, City>
{

	public BulletCityCollision()
	{
		super();
	}
	
	public BulletCityCollision(SpriteGroup<Bullet> s1, SpriteGroup<City> s2)
	{
		super(s1, s2);
	}
	
	
	
	@Override
	public void collided(Bullet s1, City s2) 
	{
		s1.setActive(false);
		s2.damage();
	}
	
}
