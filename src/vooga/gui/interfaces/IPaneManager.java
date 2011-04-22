package vooga.gui.interfaces; 

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
	 * Return to the VOOGA main menu or an in-game menu.
	 */
	public void mainMenu();
	
	/**
	 * Execute an action according to the integer code.
	 * @param id an action ID number sent from a PopoverPane, most likely a MenuPane
	 */
	public void sendAction(int id);
}
