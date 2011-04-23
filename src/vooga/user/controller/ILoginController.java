package vooga.user.controller;
/**
 * Interface for the Controller in the MVC pattern for the login structure.
 * 
 * @author Conrad.
 */
public interface ILoginController
{

/**
 * This method displays an error on the screen when the user enters incorrect input as a password
 */
	public void displayError(String s);
	
/**
 * Process information is called by the view to evaluate the input entered in the GUI panel.
 * Method also returns an optional boolean of whether or not the file can be processed
 */
	public boolean processInformation(String[] prompt, String[] text);

/**
 * This method verifys that a log-in password and username exist in a given password bank or database
 */
	public void approveLogin(String user, String password);

/**
 * Update with input is called by the model to update the prompt questions in the view. Uber helpful
 */
	public void updateWithInput();
	
/**
 * This method saves the VoogaUser information and logs out of the system
*/
	public void logOut() throws Exception;
}