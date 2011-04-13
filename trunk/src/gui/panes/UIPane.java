package gui.panes;

import java.awt.Graphics;

/**
 * Abstract UI class. Extend and customize this as desired. Call update when your
 * program updates, sending it any relevant info. This could be score, height,
 * width, etc, etc.
 * 
 * The important idea is to abstract your UI away from your gamestate. You can draw
 * all your players, then simply call this method after, which could contain a border,
 * display of your health, display of your score, or any number of other things.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public abstract class UIPane {
	
	/**
	 * Initialize components
	 */
	public abstract void init();
	
	/**
	 * Update based on the state of your game.
	 */
	public abstract void update();
	
	/**
	 * Render the components of the UI
	 * @param g
	 */
	public abstract void render(Graphics g);
}
