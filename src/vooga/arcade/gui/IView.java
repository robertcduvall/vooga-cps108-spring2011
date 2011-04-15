package vooga.arcade.gui;
import vooga.arcade.gui.Display;

/**
 * Generic View Interface in the MVC pattern.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public interface IView {
	/**
	 * Clears the display.
	 */
	public void clear();

	/**
	 * Updates the view appropriately
	 */

	/**
	 * Handles a particular error message in a dump specifically for errors.
	 * 
	 * @param s
	 */
	public void showError(String s);

	public void updateList(String s);

	void update(Display d);
}
