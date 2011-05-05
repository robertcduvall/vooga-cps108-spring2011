package games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;

/**
 * Gives any class which implements this the ability to update a sprite in the same way that sprite is 
 * updated in sprites.improvedsprites.interfaces.ISprite 
 * @author Julian
 *
 */
public interface ISpriteUpdater
{

    void update(Sprite s, long elapsedTime);
    
    
}
