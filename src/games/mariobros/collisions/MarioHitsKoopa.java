package games.mariobros.collisions;

import games.mariobros.sprites.Koopa;
import games.mariobros.sprites.Lander;
import vooga.collisions.collisionManager.BasicCollisionGroup;


public class MarioHitsKoopa extends BasicCollisionGroup<Koopa, Lander>
{
  
    @Override
    public boolean areCollide(Koopa ball, Lander paddle)
    {
      return false;
    }
    
 
    @Override
    public void collided (Koopa ball, Lander paddle)
    {
       
    }
}
