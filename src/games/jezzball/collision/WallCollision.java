package games.jezzball.collision;

import games.jezzball.sprite.Ball;
import games.jezzball.sprite.Tile;
import vooga.collisions.collisionManager.BasicCollisionGroup;

public class WallCollision extends BasicCollisionGroup<Ball, Tile>{

    @Override
    public void collided(Ball b, Tile t) {
        //TODO ball bouncing off the wall
        
    }

}
