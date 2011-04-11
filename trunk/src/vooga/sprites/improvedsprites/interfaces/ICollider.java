package vooga.sprites.improvedsprites.interfaces;

import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;
import collisions.collisionshapes.CollisionShape;

public interface ICollider
{
    
    List<Line2D> getCollisionLines();
    
    boolean collidesWith(ICollider o);

    
}
