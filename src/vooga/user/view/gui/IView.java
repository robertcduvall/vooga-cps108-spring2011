package vooga.user.view.gui;
import vooga.user.model.LoginTemplate;
import vooga.user.voogauser.Display;

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

	void update(LoginTemplate[] template);
}
