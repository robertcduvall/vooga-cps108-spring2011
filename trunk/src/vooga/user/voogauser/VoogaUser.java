package vooga.user.voogauser;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vooga.arcade.controller.ArcadeController;


/**
 * @author Conrad Haynes
 * A VoogaUser simply represents the object player whom will be playing throughout all the games. 
 * The user will be able to view, set, customize, and edit the player at the beginning of every
 * vooga session.
 */
public class VoogaUser {

	public static final String DEFAULT_NAME = "User";
	public static List<UserPreference> preferences;
	public HashMap<String, ArrayList<AbstractGamePreference>> gameInfo = new HashMap<String, ArrayList<AbstractGamePreference>>();
	public List<String> allGames = new ArrayList<String>(); //KEVIN WILL MAKE THIS METHOD  - getGameList
	public String myUsername;
	public Image myIcon;
	public VoogaUser myUser;
	
	
	/**
	 * This is the generic constructor for a VoogaUser with all default preferences
	 */
	public VoogaUser(){
		myUser = new VoogaUser(DEFAULT_NAME,preferences);
		preferences = new ArrayList<UserPreference>();	
		createGameMapping();
	}

	public VoogaUser(String username,List<UserPreference> list){
		preferences = list;
		myUsername = username;
		createGameMapping();
	}
	
	/**
	 * This method adds any additional abstractPreference to the user
	 */
	public void add(UserPreference preference){
		myUser.getPreferenceList().add(preference);
	}
	
	/**
	 * This method goes about adding specific game preferences (ie. highscore,rank) to a specific game from our arcade
	 */
	public void addGamePreference(String specificGame, AbstractGamePreference preference){
		ArrayList<AbstractGamePreference> gameReferences = gameInfo.get(specificGame);
		gameReferences.add(preference);
	}
	
	/**
	 * This method create a game to preference mapping from the passed-in list of arcade games
	 */
	private void createGameMapping() {
		for(String gameReference : allGames){
			gameInfo.put(gameReference, new ArrayList<AbstractGamePreference>());
		}
	}
	
	/**
	 * This get method retrieves a list of user preferences
	 */
	public List<UserPreference> getPreferenceList(){
		return preferences;
	}
	
	/**
	 * This method removes all user preferences from the preference list - used when incorrect input is stored
	 */
	public void removeAllPreferences(){
		preferences.clear();
	}
	
	/**
	 * This method accesses the current username and changes it to the String passed in 
	 */
	public void setUsername(String s){
		myUsername =  s;
	}

	/**
	 * returns the name of the VoogaUser
	 */
	public String getUsername(){
		return myUsername;
	}
	
	public void setIcon(Image icon){
		myIcon = icon;
	}
	public Image getImageIcon(){
		return myIcon;
	}
}
