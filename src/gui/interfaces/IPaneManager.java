package gui.interfaces;

import com.golden.gamedev.object.Sprite;

/**
 * Required interface for addition of popover panes to the gameworld.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public interface IPaneManager {

	/**
	 * Disable all popoverPanes
	 */
	public void closePanes();
	
	/**
	 * Return to the VOOGA main menu
	 */
	public void mainVoogaMenu();
	
	/**
	 * Execute an action according to the integer code.
	 * @param id an action ID number sent from a PopoverPane, most likely a MenuPane
	 */
	public void sendAction(int id);
	
	/**
	 * Sends text from an input field back to the VoogaGame
	 * @param s the string to send
	 * @param id an ID you assign to a textbox that is created- based on this ID
	 * the string can be interpreted in different ways.
	 */
	public void sendText(String s, int id);
	
	/**
	 * Sends a sprite from the popover back to the VoogaGame.
	 * @param s the sprite to send
	 * @param id the ID representing the purpose of this sprite to the Game.
	 */
	public void sendSprite(Sprite s, int id);
}
