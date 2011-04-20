package vooga.user.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vooga.user.controller.LoginController;
import vooga.user.main.XmlWriter;
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
	private Map<String, String> keyMap;
	private vooga.user.main.ResourceManager registrationResource = new vooga.user.main.ResourceManager("Registration");
	
	public LoginModel(LoginController pc){
		user = new VoogaUser();
		controller = pc;
		buildPasswordMap();
	}

/**
 * Build password map is called every time a model is initialized and it is used to read-in all the passwords and usernames
 * from a given file and create a map that evaluates these log-ins
 */
	private void buildPasswordMap(){
		List<String> passwords = new ArrayList<String>();
		passwords = readFile(new File("resources/PasswordResource.txt"));
		PasswordParser p = new PasswordParser();
		keyMap = p.parse(passwords);
		
	}

/**
 * Process is a method called by the controller that evaluates the information inputed by the user
 */
	public void process(String[] prompt, String[] text) {
		for(int i = 0; i < text.length; i++){
			UserPreference pref = new UserPreference(prompt[i],text[i]);
			user.add(pref);
			System.out.println("size " + user.getPreferenceList().size());
			update();
		}
	}

/**
 * The update method reads in a resource file and composes the prompt questions in the GUI layout
 */
	public LoginTemplate[] update() {
		int x = 0;
		String[] headerSections = registrationResource.getStringArray("Section");
		LoginTemplate[] updateInformation = new LoginTemplate[headerSections.length];
		for (int p = 0; p < headerSections.length; p++) {
			String[] sectionTitle = registrationResource
					.getStringArray(headerSections[p]);
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
	public void verifyPassword(String user2, String password) {
		if (keyMap.containsKey(user2)) {
			String correctPassword = keyMap.get(user2);
			if (correctPassword.equals(password)) {
				System.out.print("Correct");
				try {
					user = XmlWriter.readXML("resources/first.xml");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(user.myName);
		} else {
			controller.displayError("Incorrect Password");
		}
	}
	
	public LoginTemplate[] createDefaultDisplay() {
		String[] one = {"Username","Password"}; String[] two = {};
		String image = "resources/Turtle.gif";
		LoginTemplate[] log = {new LoginTemplate("Login", one,image,2), new LoginTemplate("New User",two,image,3)};
		return log;
	}
	
	public VoogaUser getVoogaUser(){
		VoogaUser copy = user;
		return copy;
	}
	
	private List<String> readFile(File file) {
		List<String> fileLines = new ArrayList<String>();
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream("resources/PasswordResource.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataInputStream in = new DataInputStream(inputStream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				fileLines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileLines;
	}
	
	public void writeFile(String filename, String text) throws IOException {
		try{BufferedWriter out = new BufferedWriter(new FileWriter(
		          filename, true));
		      out.write(text + "/n");
		      out.newLine();
		      out.close();
		      System.out.println("String is appended.");
		    } catch (IOException e) {
		    }
		  }
	}

