package vooga.arcade.view.buttonToolBar;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.actions.ActionFactory;
import vooga.arcade.view.helper.ResourceManager;
import vooga.user.voogauser.VoogaUser;

/**
 * Creates the entire component that represents the ButtonToolBar with search area.
 * Icons found on Google images.
 */

public class ButtonBar {
	private static ResourceManager buttonBarResource = new ResourceManager(
			"vooga.arcade.resources.ToolbarButtonsResource");
	private static String[] toolBarEntriesImageNames = buttonBarResource
    .getStringArray("ToolBar");
	private static ArcadeController c;

	public static JToolBar createButtonToolBar(ArcadeController p, String[] entryNames) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		for (int k=0; k < entryNames.length; k++) {
		    String entry = entryNames[k];
			String buttonName = buttonBarResource.getString(entry);
			Icon buttonIcon = new ImageIcon("src/" + buttonName);
			if((k==0) && !(entry.equals("Login")))
			{
			  //TODO: Get user icon from Conrad
			  Icon userIcon = new ImageIcon();
			  ButtonBarItem b = new ButtonBarItem(userIcon, "User", p);
			  toolBar.add(b);
			}
			else 
			{
			    ButtonBarItem b = new ButtonBarItem(buttonIcon, entry, p);
	            toolBar.add(b);  
			}   
			if (k < entryNames.length-1)
			    toolBar.addSeparator(new Dimension(400, 1));
		}
		
		JTextField searchField = new JTextField();
		searchField.addActionListener(ActionFactory.createAction("ButtonBarSearchAction", p));
		toolBar.add(searchField);
		return toolBar;
	}
	
	public static JToolBar createButtonToolBar(ArcadeController p)
	{
	    return ButtonBar.createButtonToolBar(p, toolBarEntriesImageNames);
	}
	
	public static void swapButtons(String username)
	{
	    if (username.equals("Login"))
	    {
	        JToolBar newBar = createButtonToolBar(c);
	        c.updateButtonBar(newBar);     
	    }
	    else{
	        String[] entryNames = toolBarEntriesImageNames;
	        entryNames[0] = username;
	        JToolBar newBar = createButtonToolBar(c, entryNames);
	        c.updateButtonBar(newBar);
	    }
	}
}
