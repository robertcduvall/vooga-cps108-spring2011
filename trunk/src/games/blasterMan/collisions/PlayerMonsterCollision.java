package games.blasterMan.collisions;

import games.blasterMan.sprites.Monster;
import games.blasterMan.sprites.PlayerType;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.spritegroups.SpriteGroup;

public class PlayerMonsterCollision  extends BasicCollisionGroup<PlayerType, Monster>{
	public PlayerMonsterCollision()
	{
		super();
	}
	public PlayerMonsterCollision(SpriteGroup<PlayerType> s1,
			SpriteGroup<Monster> s2)
	{
		super(s1,s2);
	}
	@Override
	public void collided(PlayerType s1, Monster s2) {
		s1.damage();
		s2.setActive(false);
		System.out.println("ouch");
	}
}
