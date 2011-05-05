package games.patterson_game.refactoredVooga.sprites.ai;

import games.patterson_game.refactoredVooga.sprites.improvedsprites.Sprite;
import java.util.ArrayList;
import java.util.EventListener;

import com.golden.gamedev.object.SpriteGroup;


/**
 * the FieldOfView contains the current perception of the AISprite. When making
 * decisions, the AISprite will only consider the sprites contained within its
 * own FieldOfView
 * @author Shun
 *
 */
public class FieldOfView {
	private ArrayList<SpriteGroup> mySpriteGroups;
	
	/**
	 * holds all the elements of the game that this character knows about
	 */
	public FieldOfView() {
		
	}
		
	public void addSpriteGroup(SpriteGroup spriteGroup) {
		
	}
	
	public void removeSpriteGroup(SpriteGroup spriteGroup) {
		
	}
	
	/**
	 * this method returns the requested SpriteGroup and checks if the requested
	 * spriteGroup is contained within the FieldOfView. 
	 * @param name
	 * @return null
	 */
	public SpriteGroup getSpriteGroup(String name) {
		return null;		
	}
	
	/**
	 * @deprecated 
	 * add a listener to a particular game element
	 * @param listener
	 */
	public void addInputListener(EventListener listener) {
		
	}
	
	/**
	 * remove a listener 
	 * @param listener
	 * @deprecated 
	 */
	public void removeInputListener(EventListener listener) {
		
	}
	
	/**
	 * @deprecated 
	 * updates the field of view when an event relevant to the AI player occurs
	 */
	public void update() {
		
	}

}
