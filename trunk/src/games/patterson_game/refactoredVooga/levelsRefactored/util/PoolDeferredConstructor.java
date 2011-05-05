package games.patterson_game.refactoredVooga.levelsRefactored.util;

import vooga.sprites.improvedsprites.Sprite;
import java.util.List;


/**
 * Deferred constructor class for sprite objects. Used by the sprite pool in the level
 * class.
 * @author Sterling Dorminey
 *
 */
public class PoolDeferredConstructor {
	private SpriteConstructor spriteConstructor;
	private List<String> otherAssignments;
	
	public PoolDeferredConstructor(SpriteConstructor spriteConstructor, 
			List<String> otherAssignments) {
		this.spriteConstructor = spriteConstructor;
		this.otherAssignments = otherAssignments;
	}
	
	public Sprite construct() {
		return spriteConstructor.construct(otherAssignments);
	}
	
	public String getSpriteGroup() {
		return spriteConstructor.getSpriteGroup();
	}
	
	public String getTargetName() {
		return spriteConstructor.getTargetName();
	}
}
