package games.patterson_game.collisions;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.sprites.improvedsprites.Sprite;

/**
 * Bounces the ship off of the screen edges
 * @author Andrew
 */
public class ShipEdgeCollision extends EdgeCollisionGroup
{

    public void collidedTop(Sprite s) {
        s.setSpeed(s.getHorizontalSpeed(), -s.getVerticalSpeed());
    }

    @Override
    public void collidedRight(Sprite s) {
        s.setSpeed(-s.getHorizontalSpeed(), s.getVerticalSpeed());
    }

    @Override
    public void collidedLeft(Sprite s) {
        s.setSpeed(-s.getHorizontalSpeed(), s.getVerticalSpeed());
    }

    @Override
    public void collidedBottom(Sprite s) {
        s.setSpeed(s.getHorizontalSpeed(), -s.getVerticalSpeed());  
    }

}
