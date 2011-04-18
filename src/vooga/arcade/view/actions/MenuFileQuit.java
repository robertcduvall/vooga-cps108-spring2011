package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;
import vooga.arcade.controller.IController;


/**
 * The action associated with clicking the File -> Quit menu bar.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */

public class MenuFileQuit extends AbstractVoogaAction {
	public MenuFileQuit(IController p) {
		super(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
