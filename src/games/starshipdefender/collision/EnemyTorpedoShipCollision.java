package games.starshipdefender.collision;


import games.starshipdefender.gameobjects.PlayerShip;
import games.starshipdefender.gameobjects.Torpedo;
import games.starshipdefender.gameobjects.TargetingImage;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class EnemyTorpedoShipCollision extends BasicCollisionGroup<Torpedo, PlayerShip>
{

    @Override
    public void collided(Torpedo s1, PlayerShip s2)
    {
        s1.setActive(false);
        TargetingImage.myHealth -= 10;
    }

}
