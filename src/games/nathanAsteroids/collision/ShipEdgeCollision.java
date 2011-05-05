package games.nathanAsteroids.collision;

import vooga.collisions.collisionManager.boundaries.EdgeCollisionGroup;
import vooga.collisions.collisionManager.boundaries.EdgeSprite;
import vooga.sprites.improvedsprites.Sprite;

public class ShipEdgeCollision extends EdgeCollisionGroup {

    @Override
    public void collidedTop(Sprite s) {
        if (s.getCenterY() < 0)
            s.setY(this.getGroup2().getBackground().getHeight() + s.getY());
    }

    @Override
    public void collidedRight(Sprite s) {
        if (s.getCenterX() > this.getGroup2().getBackground().getWidth())
            s.setX(s.getX() - this.getGroup2().getBackground().getWidth());
    }

    @Override
    public void collidedLeft(Sprite s) {
        if (s.getCenterX() < 0)
            s.setX(this.getGroup2().getBackground().getWidth()+s.getX());
    }

    @Override
    public void collidedBottom(Sprite s) {
        if (s.getCenterY() > this.getGroup2().getBackground().getHeight())
            s.setY(this.getGroup2().getBackground().getHeight()-s.getY());
    }

}
