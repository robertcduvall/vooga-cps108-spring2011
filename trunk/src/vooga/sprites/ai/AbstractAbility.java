package vooga.sprites.ai;

import java.util.ArrayList;

import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * Ability classes contain the behavior of an AIsprite. 
 * @author Shun
 */
public abstract class AbstractAbility {
	
	/**
	 * the run method can act on Sprites or SpriteGroups. The method calculates
	 * the AI reaction to the current scene or state of the game. This state
	 * or situation is contained the FieldOfView. Different sprites/spriteGroups
	 * can each call the same Ability because the run method acts only on the 
	 * sprite that is passed as a parameter
	 * @param sprite
	 * @param vision
	 */
	public void run(Sprite sprite, FieldOfView vision){
		
	}
	
	/**
	 * the run method can act on Sprites or SpriteGroups. The method calculates
	 * the AI reaction to the current scene or state of the game. This state
	 * or situation is contained the FieldOfView. Different sprites/spriteGroups
	 * can each call the same Ability because the run method acts only on the 
	 * sprite that is passed as a parameter
	 * @param spriteGroup
	 * @param vision
	 */
	public void run(SpriteGroup spriteGroup, FieldOfView vision) {
		
	}
	

}
