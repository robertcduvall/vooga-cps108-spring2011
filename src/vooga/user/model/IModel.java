package vooga.user.model;

/**
 * Model Interface for the MVC pattern.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public interface IModel
{
	/**
	 * Initialize the model
	 * 
	 * @param o
	 */
	public void initialize();

	/**
	 * Process the Object.
	 * 
	 * @param o
	 *            the Object processed by this model.
	 */
	public void evaluate(String s);

}
