package games.patterson_game.refactoredVooga.sprites.ai;

import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ISpriteBase;

/**
 * An AISprite is an extension of the sprite class, which includes 
 * computer - controlled behavior
 * 
 * AISprite catches events and then registers event handlers which invoke the 
 * Abilities that the AISprite has. 
 * @author Shun
 *
 */
public class AISprite{
	FieldOfView myVision;
	EventManager myManager;
	
	/**
	 * The constructor includes an EventManager, to allow AISprite to receive
	 * events. 
	 * 
	 * The current perception of the AISprite is the FieldOfView associated 
	 * with the current sprite 
	 * 
	 * In the constructor, any Abilitys that the AISprite has are also 
	 * initialized so that they may be called later. 
	 * 
	 * @param manager
	 * @param vision
	 */
	public AISprite(EventManager manager, FieldOfView vision){
		
	}	
	
	/**
	 * the developer using this class would include any number of 
	 * eventHandlers for events that the AISprite would handle. 
	 */
}
