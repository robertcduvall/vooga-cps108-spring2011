package games.patterson_game.collisions;

import games.patterson_game.refactoredVooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;

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
