package vooga.arcade.view.buttonToolBar;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import vooga.arcade.controller.IController;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.helper.ResourceManager;

/**
 * Creates the entire component that represents the ButtonToolBar with search area.
 * Icons found on Google images.
 */

public class ButtonBar {
	private static ResourceManager buttonBarResource = new ResourceManager(
			"vooga.arcade.resources.ToolbarButtonsResource");

	public static JToolBar createButtonToolBar(IController p) {
		JToolBar toolBar = new JToolBar();
		String[] toolBarEntriesImageNames = buttonBarResource
				.getStringArray("ToolBar");
		toolBar.setFloatable(false);
		
		for (int k=0; k < toolBarEntriesImageNames.length; k++) {
		    String entry = toolBarEntriesImageNames[k];
			String buttonName = buttonBarResource.getString(entry);
			Icon buttonIcon = new ImageIcon("src/" + buttonName);
			ButtonBarItem b = new ButtonBarItem(buttonIcon, entry, p);
			toolBar.add(b);
			if (k < toolBarEntriesImageNames.length-1)
			    toolBar.addSeparator(new Dimension(400, 1));
		}
		
		JTextField searchField = new JTextField();
		toolBar.add(searchField);
		return toolBar;
	}
}
