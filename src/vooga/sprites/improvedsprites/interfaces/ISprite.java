package vooga.sprites.improvedsprites.interfaces;

import vooga.collisions.shapes.regularShapes.Polygon;

import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import com.golden.gamedev.object.collision.CollisionShape;


public interface ISprite 
{


    
    /**
     *  updates the sprite
     * @param elapsedTime
     */
    public void update (long elapsedTime);
    
    
}
