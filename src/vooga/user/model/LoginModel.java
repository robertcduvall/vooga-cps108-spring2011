package vooga.user.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vooga.user.controller.LoginController;
import vooga.user.main.ResourceManager;
import vooga.user.main.XmlWriter;
import vooga.user.model.parser.PasswordEncoding;
import vooga.user.model.parser.PasswordParser;
import vooga.user.model.parser.RegXParser;
import vooga.user.voogauser.UserPreference;
import vooga.user.voogauser.VoogaUser;



/**
 * The Model for the VoogaLogin game program. It perform a lot of the background work for the code and provides update Templates
 * @author Conrad Haynes
 */
public class LoginModel
{
	private VoogaUser user;
	private LoginController controller;
	private RegXParser myRegEx;
	private SQLite database;
	private final static String USER_TABLE = "user";
	
	private ResourceManager registrationResource = new ResourceManager("vooga.user.resources.Registration");
	private ResourceManager regExResource = new ResourceManager("vooga.user.resources.RegularExpressionResource"); 
	
	public LoginModel(LoginController pc){
		user = new VoogaUser();
		controller = pc;
		myRegEx =new RegXParser();
	}


/**
 * Process is a method called by the controller that evaluates the information inputed by the user
 */
	public boolean process(String[] prompt, String[] text){
		for (int i = 0; i < text.length; i++) {
			if (myRegEx.verifyRegex(prompt[i], text[i])) {
				user.add(new UserPreference(prompt[i], text[i]));
				update();
			} else {
				controller.displayError("Incorrect Input Error " + regExResource.getString(prompt[i] + "X"));
				user.removeAllPreferences();
				return false;
			}
		}
		database = new SQLite();	
		database.addNewUser(USER_TABLE, text);
		database.close();
		return true;
	}

/**
 * The update method reads in a resource file and composes the prompt questions in the GUI layout
 */
	public LoginTemplate[] update() {
		int x = -1;
		String[] headerSections = registrationResource.getStringArray("Section");
		LoginTemplate[] updateInformation = new LoginTemplate[headerSections.length];
		for (int p = 0; p < headerSections.length; p++) {
			String[] sectionTitle = registrationResource.getStringArray(headerSections[p]);
			String[] text = new String[sectionTitle.length];
			if (p == headerSections.length - 1) {
				x = 1;
			}
			for (int i = 0; i < sectionTitle.length; i++) {
				text[i] = registrationResource.getString(sectionTitle[i]);
			}
			updateInformation[p] = new LoginTemplate(headerSections[p], text,null, x);
		}
		return updateInformation;
	}

	/**
	 * This method uses the password map to determine if the user has a pre-existing VoogaUser account to operate through
	 */
	public boolean verifyPassword(String user, String password) {
				database = new SQLite();
		return password.matches(database.retrieveExactEntry(USER_TABLE, user, "Password"));
	}

	/**
	 * This method creates the default log in sequence for this gui
	 */
	public LoginTemplate[] createDefaultDisplay() {
		String[] loginPrompt = registrationResource.getStringArray("DefaultLoginPrompt");
		String[] one = {loginPrompt[0],loginPrompt[1]}; String[] two = {};
		String image = "src/vooga/user/resources/DefaultLoginImage.png";
		LoginTemplate[] log = {new LoginTemplate(loginPrompt[2], one,image,0), new LoginTemplate(loginPrompt[3],two,image,2)};
		return log;
	}
	
	public VoogaUser getVoogaUser(){
		return user;
	}
	}

