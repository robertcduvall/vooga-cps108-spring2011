package vooga.user.controller;
import java.awt.Dimension;
import java.util.List;
import vooga.arcade.controller.*;
import vooga.user.model.LoginModel;
import vooga.user.model.LoginTemplate;
import vooga.user.view.LoginViewer;
import vooga.user.voogauser.UserPreference;
import vooga.user.voogauser.VoogaUser;

public class LoginController implements ILoginController
{
	private LoginViewer view;
	public ArcadeController arcadeController;
	public LoginModel model;
	public List<String> gameReferences;

	public LoginController(String string, String string2, int i, int j, List<String> arcadeGames)
	{
		arcadeController = new ArcadeController();
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
	public boolean approveLogin(String user, String password) {
		return model.verifyPassword(user, password);		
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
	

/**
 * This method exits the loginButton and 
 */
	@Override
	public void exitLogin() throws Exception{
		VoogaUser user = model.getVoogaUser();
		String username = "";
		for(UserPreference p : user.getPreferenceList()){
			if(p.getPrompt().equals("UserName")){username = p.getInput();}
		}
		System.out.println(username);
		arcadeController.swapToolbarButton(user);
		System.out.println("I need indication!!!!!!");
	}
}
