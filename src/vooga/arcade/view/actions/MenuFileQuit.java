package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;
import vooga.arcade.controller.ArcadeController;


/**
 * The action associated with clicking the File -> Quit menu bar.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */

public class MenuFileQuit extends AbstractVoogaAction {
	public MenuFileQuit(ArcadeController p) {
		super(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
