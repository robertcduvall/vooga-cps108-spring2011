package vooga.arcade.view.buttonToolBar;


import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import vooga.arcade.controller.IController;
import vooga.arcade.view.helper.ResourceManager;
import vooga.arcade.view.actions.ActionFactory;

/**
 * This class represents a Button for the Button Toolbar that grabs its actions
 * with the action factory.
 * 
 */
public class ButtonBarItem extends JButton {
	private static final long serialVersionUID = 1L;
	private static final Insets margins = new Insets(0, 0, 0, 0);

	private static ResourceManager buttonsResource = new ResourceManager(
			"vooga.arcade.resources.ToolbarButtonsResource");

	private static String className = "ButtonBarItem";

	public ButtonBarItem(Icon icon, String buttonName, IController p) {
		super(icon);
		//ActionListener aa = ActionFactory.createAction(className + buttonName,
				//p);
		//this.addActionListener(aa);

		setMargin(margins);
		setVerticalTextPosition(BOTTOM);
		setHorizontalTextPosition(CENTER);
	}

	public ButtonBarItem(String buttonName, IController p) {
		this(new ImageIcon(buttonsResource.getString(buttonName)), buttonName,
				p);
	}

}