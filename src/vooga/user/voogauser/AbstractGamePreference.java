package vooga.user.voogauser;


/**
 * @author Conrad Haynes
 * GameUserPreferences are used to denote all the information that a player can hold that is not filled in when the player is
 * registered. This abstract holder represents a generic holder for all of these properties/information. 
 */
public abstract class AbstractGamePreference extends UserPreference{
	private Boolean myFavorite;
	
	public AbstractGamePreference(){
	}
	
	/**
	 * This abstract constructor is used to create a game preference, which is essentially contains a certain subset of Games from
	 * the arcade that apply to the given preference. 
	 */
	public AbstractGamePreference(String preferenceType, String value){
		super(preferenceType, value);
	}
	
	/**
	 * Currently favorite is entered as a boolean value, i.e. a game is or is not a favorite
	 */
	public AbstractGamePreference(Boolean favorite){
		myFavorite = favorite;
	}

	/**
	 * This method, when called, returns the user input for a specific game preference
	 */
	public String getInput() { 
		return super.getInput();
	}
	
	/**
	 * For a specific game preference the information can be editedd or changed using this method
	 */
	public void editInput(String newInput) {
		super.editInput(newInput);
	}

	/**
	 * This button toggles on and off the favorite feature
	 */
	public void toggleBoolean(){
		if(myFavorite == false){
			myFavorite = true;
		}else{myFavorite=false;}
		}
	}
