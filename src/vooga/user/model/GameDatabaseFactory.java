package vooga.user.model;

import java.sql.SQLException;
import java.util.List;

import vooga.user.controller.ILoginController;
import vooga.user.main.ResourceManager;
import vooga.user.model.database.GameDatabase;
import vooga.user.model.database.UserDatabase;

public class GameDatabaseFactory {
	private List<String> myGameReferences;
	private ILoginController myController;
	private static final String DEFAULTGAME_TABLE = "game";
	private final static String USER_TABLE = "user";
	public GameDatabase gameDatabase;
	public UserDatabase userDatabase;
	
	private ResourceManager registrationResource = new ResourceManager("vooga.user.resources.Registration");

	public GameDatabaseFactory(List<String> gameReferences, ILoginController pc){
		myController = pc;
		myGameReferences = gameReferences;
		confirmDatabases(gameReferences);
	}
	
	public GameDatabaseFactory(){
	}
	
	private void confirmDatabases(List<String> gameReferences) {
		try {
			userDatabase = new UserDatabase();
		} catch (SQLException e4) {
			e4.printStackTrace();
		} catch (ClassNotFoundException e4) {
			e4.printStackTrace();
		}
		for(String gameTitle : gameReferences){
		try {
			gameDatabase = new GameDatabase();
		} catch (SQLException e3) {
			e3.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}	
		try {
			gameDatabase.initialize(gameTitle, registrationResource.getStringArray("AbstractGamePreferences"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> allUsers = null;
		try {
			allUsers = userDatabase.retrieveTableColumn(USER_TABLE, "UserName");
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		List<String> currentUsers = null;
		try {
			currentUsers = gameDatabase.retrieveTableColumn(gameTitle, "UserName");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		for(String user : allUsers){
			if(currentUsers == null || currentUsers.contains(user)){
				String[] temp = registrationResource.getStringArray("DefaultGamePreference");
				
				try {
					gameDatabase.addNewUser(gameTitle,addUser(user,temp));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		}
	}
	
	private String[] addUser(String user, String[] preferences){
		String[] adjustedUsers = new String[preferences.length+1];
		adjustedUsers[0] = user;
		for(int i = 0; i < preferences.length; i++){
			adjustedUsers[i+1] = preferences[i];
		}
		return adjustedUsers;	
	}
	
	private void updateStats(){
		
	}
	
	
}
