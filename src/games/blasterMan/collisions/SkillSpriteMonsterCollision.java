package games.blasterMan.collisions;

import games.blasterMan.sprites.Monster;
import games.blasterMan.sprites.SkillSprite;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.spritegroups.SpriteGroup;

public class SkillSpriteMonsterCollision extends BasicCollisionGroup<SkillSprite, Monster>{
	public SkillSpriteMonsterCollision()
	{
		super();
	}
	public SkillSpriteMonsterCollision(SpriteGroup<SkillSprite> s1,
			SpriteGroup<Monster> s2)
	{
		super(s1,s2);
	}
	@Override
	public void collided(SkillSprite s1, Monster s2) {
		s1.setActive(false);
		s2.setActive(false);
	}

}
