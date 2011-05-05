package vooga.user.controller;
import java.awt.Dimension;
import java.util.List;
import vooga.arcade.controller.*;
import vooga.user.model.GameDatabaseFactory;
import vooga.user.model.LoginModel;
import vooga.user.model.LoginTemplate;
import vooga.user.view.LoginViewer;
import vooga.user.view.gui.middleFrame.ViewInformationSection;
import vooga.user.view.gui.middleFrame.InputSection;
import vooga.user.view.gui.middleFrame.TextSection;
import vooga.user.voogauser.UserPreference;
import vooga.user.voogauser.VoogaUser;
/**
 * @author Conrad Haynes
 * This controller is the main hub that enable s communication between the model and the view. All information passes through here
 */
public class LoginController implements ILoginController
{
	private LoginViewer view;
	public ArcadeController arcadeController;
	public LoginModel model;
	public VoogaUser myUser;
	public List<String> gameReferences;
	public GameDatabaseFactory factory;

	public LoginController(String string, ArcadeController controller, int i, int j, List<String> arcadeGames)
	{
		arcadeController = controller;
		model = new LoginModel(this);
		view = new LoginViewer(string, new Dimension(i, j), this);
		gameReferences = arcadeGames;
		factory = new GameDatabaseFactory(gameReferences,this);
		
	}

	public LoginController(){}

	@Override
	public void displayError(String s){
		view.showError(s);
	}

	@Override
	public void updateWithInput(){
		view.update(model.createPrompts(new InputSection()));	
	}
	
	public void updateWithInformationPanel(){
		view.update(model.createPrompts(new ViewInformationSection()));
	}
	
	@Override
	public boolean processInformation(String[] text) {
		 return model.process(text);
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
	
	public VoogaUser getVooga(){
		return model.getVoogaUser();
	}
	
/**
 * This method exits the loginButton
 */
	@Override
	public void logIn(){
		myUser = model.getVoogaUser();
		System.out.println("Username is " + myUser.getUsername());
		arcadeController.swapToolbarButton(myUser);
		view.setVisible(false);
	}
	@Override
	public void logOut(){
		myUser.removeAllPreferences();
		view.setVisible(false);
	}
}
