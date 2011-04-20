package vooga.sprites.improvedsprites.interfaces;

import vooga.collisions.shapes.regularShapes.Polygon;

import vooga.collisions.shapes.collisionShapes.ICollisionShape;
import com.golden.gamedev.object.collision.CollisionShape;

/**
 * Defines the essential methods that make a sprite a sprite!
 * @author Julian
 *
 */
public interface ISprite extends ISpriteBase
{


    
    /**
     *  updates the sprite based on an elapsed time retrieved from the gamestate
     * @param elapsedTime
     */
    public void update (long elapsedTime);
    
    
}
