package games.starshipdefender.collision;

import games.starshipdefender.gameobjects.EnemyShip;
import games.starshipdefender.gameobjects.Torpedo;
import vooga.collisions.collisionManager.BasicCollisionGroup;
import vooga.sprites.spritegroups.SpriteGroup;

public class TorpedoEnemyShipCollision extends BasicCollisionGroup<Torpedo, EnemyShip>
{
    public TorpedoEnemyShipCollision()
    {
        super();
    }

    public TorpedoEnemyShipCollision(SpriteGroup<Torpedo> s1, SpriteGroup<EnemyShip> s2)
    {
        super(s1, s2);
    }
    
    @Override
    public void collided(Torpedo s1, EnemyShip s2)
    {
        s1.setActive(false);
        s2.setActive(false);
    }

}
