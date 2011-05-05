package games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces;

import games.patterson_game.refactoredVooga.collisions.shapes.collisionShapes.ICollisionShape;
import games.patterson_game.refactoredVooga.collisions.shapes.regularShapes.Polygon;

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
