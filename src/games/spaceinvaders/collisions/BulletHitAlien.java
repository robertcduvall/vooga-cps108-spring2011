package games.spaceinvaders.collisions;

import games.breakout.sprites.Ball;
import games.breakout.sprites.Block;
import games.spaceinvaders.Commons;
import games.spaceinvaders.sprites.Alien;
import games.spaceinvaders.sprites.Shot;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class BulletHitAlien extends BasicCollisionGroup<Shot, Alien> implements Commons
{
    @Override
    public void collided(Shot shot, Alien alien)
    {
        //alien.setImage(getImage(EXPLOSION));
        alien.setDying(true);
        shot.die();       
        int y = (int) shot.getY();
        y -= 4;
        if (y < 0) shot.die();
        else shot.setY(y);
    }
    
    @Override
    public boolean areCollide(Shot shot, Alien alien)
    {
        int alienX = (int) alien.getX();
        int alienY = (int) alien.getY();
        int shotX = (int) shot.getX();
        int shotY = (int) shot.getY();
        
        return alien.isVisible() && shot.isVisible() && shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH) && shotY >= (alienY) && shotY <= (alienY+ALIEN_HEIGHT);
    }
}
