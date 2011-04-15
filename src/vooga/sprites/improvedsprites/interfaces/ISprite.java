package vooga.sprites.improvedsprites.interfaces;

import vooga.collisions.shapes.regularShapes.Polygon;

import com.golden.gamedev.object.collision.CollisionShape;


public interface ISprite 
{

    /**
     * Returns default {@linkplain #defaultCollisionShape collision shape}, can
     * be used along with collision manager.
     */
    public abstract Polygon getCollisionShape ();


    public abstract void setCollisionShape (Polygon cs);
    
    /**
     *  updates the sprite
     * @param elapsedTime
     */
    public void update (long elapsedTime);
    
    
}
