package games.patterson_game.refactoredVooga.collisions;

import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.ICollisionShape;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.image.BufferedImage;
import java.util.List;

public interface ICollidable
{
    
    /**
     * Returns default {@linkplain #defaultCollisionShape collision shape}, can
     * be used along with collision manager.
     */
    public abstract <T extends ICollisionShape> T getCollisionShape ();


    public abstract void setCollisionShape (ICollisionShape cs);    
    
}
