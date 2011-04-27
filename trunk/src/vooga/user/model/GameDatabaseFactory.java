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
			
		userDatabase = new UserDatabase();
		for(String gameTitle : gameReferences){
			gameDatabase = new GameDatabase();
			try {
				gameDatabase.initialize(gameTitle, registrationResource.getStringArray("AbstractGamePreferences"));
			} catch (SQLException e) {e.printStackTrace();}

		List<String> allUsers = userDatabase.retrieveTableColumn(USER_TABLE, "UserName");
		List<String> currentUsers = gameDatabase.retrieveTableColumn(gameTitle, "UserName");
		for(String user : allUsers){
			if(currentUsers == null || currentUsers.contains(user)){
				String[] temp = registrationResource.getStringArray("DefaultGamePreference");
					gameDatabase.addRow(gameTitle,addUser(user,temp));
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
