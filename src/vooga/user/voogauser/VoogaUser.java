package vooga.user.voogauser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Conrad Haynes
 * A VoogaUser simply represents the object player whom will be playing throughout all the games. 
 * The user will be able to view, set, customize, and edit the player at the beginning of every
 * vooga session.
 */
public class VoogaUser {

	public static final String DEFAULT_NAME = "User";
	public static final String DEFAULT_PASS = "Password";
	public static List<UserPreference> preferences;
	public HashMap<String, ArrayList<AbstractGamePreference>> gameInfo = new HashMap<String, ArrayList<AbstractGamePreference>>();
	public List<String> allGames = new ArrayList<String>();
	public String myUsername, myPassword;
	public VoogaUser myUser;
	
	
	/**
	 * This is the generic constructor for a VoogaUser with all default preferences
	 */
	public VoogaUser(){
		myUser = new VoogaUser(DEFAULT_NAME, DEFAULT_PASS, preferences);
		preferences = new ArrayList<UserPreference>();	
		createGameMapping();
	}

	public VoogaUser(String username, String password, List<UserPreference> list){
		preferences = list;
		myUsername = username;
		myPassword = password;
		createGameMapping();
	}
	
	/**
	 * This method adds any additional abstractPreference to the user
	 */
	public void add(UserPreference preference){
		myUser.getPreferenceList().add(preference);
	}
	
	public void addGamePreference(String specificGame, AbstractGamePreference preference){
		ArrayList<AbstractGamePreference> gameReferences = gameInfo.get(specificGame);
		gameReferences.add(preference);
	}
	
	private void createGameMapping() {
		for(String gameReference : allGames){
			gameInfo.put(gameReference, new ArrayList<AbstractGamePreference>());
		}
	}
	
	public List<UserPreference> getPreferenceList(){
		return preferences;
	}
	
	public void removeAllPreferences(){
		preferences.clear();
	}
	

}
