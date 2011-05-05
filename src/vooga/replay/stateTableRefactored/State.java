package vooga.replay.stateTableRefactored;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import vooga.replay.SpriteReplayData;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritegroups.SpriteGroup;

@SuppressWarnings("serial")
public class State implements Serializable{
	
	private Map<Sprite, SpriteReplayData> stateMap;
	
	public State(){
		stateMap = new HashMap<Sprite, SpriteReplayData>();
	}
	
	public void addSpriteGroupToState(SpriteGroup<Sprite> s){
		for (Sprite sprite : s.getSprites()) {
			if (sprite != null && sprite.isActive()) {
				stateMap.put(sprite, new SpriteReplayData(sprite, sprite.isActive()));
			}
		}
	}
	
	public void render(Graphics2D g){
		for (Sprite sprite : stateMap.keySet()) {
			sprite.render(g);
		}
	}

	public void transformSpritesToState() {
		for (Sprite sprite : stateMap.keySet()) {
			sprite = stateMap.get(sprite).transformSprite(sprite);
		}
	}

}
