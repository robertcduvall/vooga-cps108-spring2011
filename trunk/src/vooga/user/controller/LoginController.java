package vooga.user.controller;
import java.awt.Dimension;
import java.util.List;

import vooga.user.main.XmlWriter;
import vooga.user.model.LoginModel;
import vooga.user.model.LoginTemplate;
import vooga.user.view.LoginViewer;
import vooga.user.voogauser.UserPreference;
import vooga.user.voogauser.VoogaUser;

public class LoginController implements ILoginController
{
	private LoginViewer view;
	public LoginModel model;
	public List<String> gameReferences;

	public LoginController(String string, String string2, int i, int j, List<String> arcadeGames)
	{
		model = new LoginModel(this);
		view = new LoginViewer(string, string2, new Dimension(i, j), this);
		gameReferences = arcadeGames;
	}

	public LoginController(){}

	@Override
	public void displayError(String s){
		view.showError(s);
	}

	@Override
	public void updateWithInput(){
		view.update(model.update());	
	}
	
	@Override
	public boolean processInformation(String[] prompt ,String[] text) {
		 return model.process(prompt,text);
	}
	
	@Override
	public void approveLogin(String user, String password) {
		model.verifyPassword(user, password);		
	}
	
/**
 * This ad-hoc method returns the default initial screen that is shown to the user upon launch of the login screen.
 */
	public LoginTemplate[] getDefaultTemplate(){
		return model.createDefaultDisplay();
	}
	
/**
 * 	This method returns a list of all the games present in the game arcade;
 */
	public List<String> getGameReferences(){
		return gameReferences;
	}
	
	@Override
	public void logOut() throws Exception{
		VoogaUser user = model.getVoogaUser();
		String text = "";
		for(UserPreference p : user.getPreferenceList()){
			if(p.getPrompt().equals("UserName")){text = text + p.getInput() + ":";}
			if(p.getPrompt().equals("Password")){text = text + p.getInput();}
		}
		//XmlWriter.writeXML(user,"resources/first.xml");
		//model.writeFile("resources/PasswordResource.txt", text);
		System.out.close();
	}


}
