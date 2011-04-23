package vooga.user.view;
import vooga.user.model.LoginTemplate;

/**
 * Generic View Interface in the MVC pattern.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public interface IView {
	
	/**
	 * Handles a particular error message in a dump specifically for errors.
	 */
	public void showError(String s);
		
	/**
	 * This common I-view method is used to update any view with the appropriate input prompts
	 */
	void update(LoginTemplate[] template);
}
