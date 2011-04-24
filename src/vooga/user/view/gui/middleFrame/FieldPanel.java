package vooga.user.view.gui.middleFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import vooga.user.controller.ILoginController;
import vooga.user.main.ResourceManager;
import vooga.user.model.LoginTemplate;
import vooga.user.view.actions.AbstractLoginAction;
import vooga.user.view.actions.PasswordLogin;
import vooga.user.view.actions.RegisterButton;
import vooga.user.view.actions.SubmitButton;

/**
 * @author Conrad Haynes
 * The generic field Panel class displays a specific "fill-in" panel for a Login sequence based on the appropriate calls
 * to builder methods.
 *
 */
public class FieldPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private List<JTextField> inputFields = new ArrayList<JTextField>();
	private List<String> promptText = new ArrayList<String>();
	private String[] inputText; 
	private ResourceManager guiResource = new ResourceManager("vooga.user.resources.GUIResource");
	
	/**
	 * This is the constructor for a Field Panel JPanel that is constructed section by section
	 */
	public FieldPanel(LoginTemplate[] log, ILoginController controller) {
		this.setLayout(new MigLayout());
		
		for (LoginTemplate login : log) {
			addSection(login.getHeader(), login.getPrompts());
			
			if (login.getImageURL() != null) {
				addImage(login.getImageURL());
			}
			AbstractLoginAction[] buttons = {
					new PasswordLogin(controller, this),
					new SubmitButton(controller, this),
					new RegisterButton(controller, this) };

			// accesses a specific button class action based on it's index in the array
			if (login.myButton >= 0) {
				addButton(buttons[login.myButton]);
			}
		}
	}
	
	 public void paintComponent(Graphics g) {
		 String[] images = guiResource.getStringArray("LoginImageArray");
		 Random rand = new Random();
		 Image img = new ImageIcon("src/vooga/user/resources/" + 
				 guiResource.getString(images[rand.nextInt(images.length)])).getImage();
		    g.drawImage(img, 0, 0, 640,480, null);
		  }

	/**
	 *This method adds a fill-in section to the gui when passed in a section title and; fill-in prompts
	 */
	public void addSection(String sectionTitle, String[] request) {
		addSeparator(this, sectionTitle);	
		for(String r : request){
			promptText.add(r);
		}		
		// The for loop creates each prompt that the operator wants to ask
		for (int i = 0; i < request.length; i++) {
			JTextField user = new JTextField(30);
			this.add(new JLabel(request[i]), "gap para");
			//System.out.println(request[i]);
			inputFields.add(user);
			//System.out.println("input size " + inputFields.size());
			this.add(user, "span, growx, wrap");
		}
		}	
	/**
	 * This method retrieves the results of all the input fields
	 */
	public String[] getInputFields(){
		inputText = new String[inputFields.size()];
		for(int j = 0; j < inputFields.size(); j++){
			inputText[j] = inputFields.get(j).getText();
		}
		return inputText;
	}
	
	/**
	 * This method retrieves the appropriate prompt inputs from the log-in sequence
	 */
	public String[] getPromptText(){
		String[] text = new String[promptText.size()];
		for(int i = 0; i < promptText.size(); i++){
			text[i] = promptText.get(i);
		}
		return text;
	}
	
	/**
	 * This method adds a button to the fill-in section to advance us to the appropriate window
	 */
	public void addButton(ActionListener listener) {
		JButton button = new JButton("NEXT");
		this.add(button, "wrap para, wrap para");
		button.addActionListener(listener);
	}
		
	/**
	 * This method is utilized by the add Section method to create different sections to the game
	 */
	 private void addSeparator(JPanel panel, String text)
	   {
	      panel.add(new JLabel(text), "gapbottom 1, span, split 2, aligny center");
	      panel.add(new JSeparator(), "gapleft rel, growx");
	   }

	 /**
	  * This method adds images to a specific section
	  */
	 public void addImage(String imagePath){
			JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
			imageLabel.setHorizontalAlignment(JLabel.CENTER);
			this.add(imageLabel);
		}
	 
	 
}
