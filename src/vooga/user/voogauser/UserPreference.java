package vooga.user.voogauser;

/**
 * @author Conrad Haynes
 *This class is a generic class for attributes that a VoogaUser holds. 
 */
public class UserPreference {
	protected String myPrompt, myInput;
	
	/**
	 * This is the generic constructor for a user preference. It is mostly implemented for reflection purposes.
	 */
	public UserPreference(){}
	
	/**
	 * This user preference constructor is used to store information that the user enters upon registration as String files.
	 */
	public UserPreference(String prompt, String input){
		myPrompt = prompt;
		myInput = input;
	}
	
	/**
	 * This getInput method return the input value for a user preference. 
	 */
	public String getInput() { 
		return myInput;
	}
	
	public String getPrompt(){
		return myPrompt;
	}
	/**
	 * This edit input method is called to allow users to edit information in regards to the their current VoogaUser.
	 * This is very useful - especially in changing names, addresses, phone numbers, e-mails etc.
	 */
	public void editInput(String newInput) {
		myInput = newInput;
	}
}
