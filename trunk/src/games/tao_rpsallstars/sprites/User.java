/**
 * 
 */
package games.tao_rpsallstars.sprites;

import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.stats.StatsMain;

/**
 * @author Kevin
 * 
 * stores score, creates RockThrows, etc that animate
 */
public class User extends Sprite{
	
	EventManager eventManager;
	
	//Implement a score tracker
	StatsMain stats;
	
	public User(EventManager eventManager){
		
	}
	
	public void rock() {
		//Create RockThrow
	}

	public void paper() {
		//Create PaperThrow
	}

	public void scissors() {
		//Create ScissorsThrow
	}
	
	
}
