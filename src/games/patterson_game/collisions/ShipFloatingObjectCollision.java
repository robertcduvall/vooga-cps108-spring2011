package games.patterson_game.collisions;

import games.patterson_game.AbstractFloatingObject;
import games.patterson_game.Ship;
import games.patterson_game.refactoredVooga.collisions.collisionManager.BasicCollisionGroup;
import games.patterson_game.refactoredVooga.sprites.spritegroups.SpriteGroup;

/**
 * Collides a floating object with the ship
 * @author Andrew
 */
public class ShipFloatingObjectCollision extends BasicCollisionGroup<Ship, AbstractFloatingObject>
{

    public ShipFloatingObjectCollision ()
    {
        super();
    }


    public ShipFloatingObjectCollision (SpriteGroup<Ship> s1, SpriteGroup<AbstractFloatingObject> s2)
    {
        super(s1, s2);
    }


    @Override
    public void collided (Ship ship, AbstractFloatingObject obstacle)
    {
        obstacle.collideWithShip(ship);
    }

}
