package vooga.user.model;

/**
 * Model Interface for the MVC pattern.
 * @Conrad Haynes
 * 
 */
public interface IModel
{
	/**
	 * This method updates the view with the appropriates prompts
	 */
	public LoginTemplate[] update();

	/**
	 * Processes the input into a VoogaUser Profile
	 */
	public boolean process(String[] prompt, String[] text);

}
