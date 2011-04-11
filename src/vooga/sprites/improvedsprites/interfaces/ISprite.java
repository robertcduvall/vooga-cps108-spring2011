package vooga.sprites.improvedsprites.interfaces;

import collisions.collisionshapes.CollisionShape;

public interface ISprite 
{

    /**
     * Returns default {@linkplain #defaultCollisionShape collision shape}, can
     * be used along with collision manager.
     */
    public abstract CollisionShape getCollisionShape ();


    public abstract void setCollisionShape (CollisionShape cs);
    
    /**
     *  updates the sprite
     * @param elapsedTime
     */
    public void update (long elapsedTime);
    
    
}
