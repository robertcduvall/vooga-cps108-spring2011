package vooga.collisions.CollisionCheckers;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.image.BufferedImage;
import java.util.List;

public interface ICollidable
{
    
    Iterable<Line2D> getCollisionLines();
    
    
}
