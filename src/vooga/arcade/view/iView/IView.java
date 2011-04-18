package vooga.arcade.view.iView;

/**
 * Generic View Interface in the MVC pattern.
 * 
 */
public interface IView {
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
}
