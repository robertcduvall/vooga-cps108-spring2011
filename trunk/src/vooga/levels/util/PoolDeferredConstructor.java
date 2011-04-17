package vooga.levels.util;

import java.util.List;

import com.golden.gamedev.object.Sprite;

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
}
