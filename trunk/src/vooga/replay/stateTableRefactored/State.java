package vooga.replay.stateTableRefactored;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import vooga.replay.SpriteReplayData;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

/**
 * 
 * Class that represents a "State in time" - A Map of Sprites to their ReplayData,
 * and the methods that are added here for ease.
 * 
 * @author CJAMES
 * 
 * For refactor project
 *
 */

@SuppressWarnings("serial")
public class State implements Serializable{
	
	private Map<Sprite, SpriteReplayData> stateMap;
	
	public State(){
		stateMap = new HashMap<Sprite, SpriteReplayData>();
	}
	
	/**
	 * Adds Sprites from sprite group to the current State.
	 * @param s Sprite group from which sprites will be added to state
	 */
	public void addSpriteGroupToState(SpriteGroup<Sprite> s){
		for (Sprite sprite : s.getSprites()) {
			if (sprite != null && sprite.isActive()) {
				stateMap.put(sprite, new SpriteReplayData(sprite, sprite.isActive()));
			}
		}
	}
	/**
	 * Renders all the sprites in this state.
	 * @param g Graphics2D
	 */
	public void render(Graphics2D g){
		for (Sprite sprite : stateMap.keySet()) {
			sprite.render(g);
		}
	}

	/**
	 * Changes each sprite being updated to match the data in their 
	 * SpriteReplayData, as found in the stateMap
	 */
	public void transformSpritesToState() {
		for (Sprite sprite : stateMap.keySet()) {
			sprite = stateMap.get(sprite).transformSprite(sprite);
		}
	}

}
