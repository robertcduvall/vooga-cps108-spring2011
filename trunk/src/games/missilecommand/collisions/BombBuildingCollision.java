package games.missilecommand.collisions;

import vooga.collisions.collisionManager.BasicCollisionGroup;

import games.missilecommand.sprites.Bomb;
import games.missilecommand.sprites.Building;

/**
 * A collision between a bomb and a building. Simply destroys both.
 * @author Alex Lee (hl69)
 */
public class BombBuildingCollision extends BasicCollisionGroup<Bomb, Building>
{

    @Override
    public void collided(Bomb s1, Building s2) 
    {
        s1.setActive(false);
        s2.setActive(false);
    }

}
