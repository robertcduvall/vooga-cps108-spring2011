package games.patterson_game.refactoredVooga.sprites.ai;

import games.patterson_game.refactoredVooga.core.event.EventManager;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import games.patterson_game.refactoredVooga.sprites.improvedsprites.interfaces.ISpriteBase;

/**
 * An AISpriteGroup is a spriteGroup that controls the behavior of a group of 
 * sprites. For example, the computer player in a TicTacToe or Chess game would 
 * be an AISpriteGroup. 
 * @author Shun
 *
 */
public class AISpriteGroup{
	FieldOfView myVision;
	EventManager myManager;
	
	/**
	 * The constructor includes an EventManager, to allow AISpriteGroup to 
	 * receive events. 
	 * 
	 * The current perception of the AISpriteGroup is the FieldOfView associated 
	 * with the current sprite 
	 * 
	 * In the constructor, any Abilitys that the AISpriteGroup has are also 
	 * initialized so that they may be called later. 
	 * 
	 * @param manager
	 * @param vision
	 */
	public AISpriteGroup(EventManager manager, FieldOfView vision){
		
	}	
	
	/**
	 * the developer using this class would include any number of 
	 * eventHandlers for events that the AISpriteGroup would handle. 
	 */
}
