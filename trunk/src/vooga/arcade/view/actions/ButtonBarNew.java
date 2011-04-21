package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import vooga.arcade.controller.ArcadeController;


/**
 * The action associated with clicking the File -> Open menu bar. Opens a
 * particular file that could represent a history of data or a previously stored
 * expression.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public class ButtonBarNew extends AbstractVoogaAction {
	public ButtonBarNew(ArcadeController p) {
		super(p);
	}

	private static final JFileChooser fc = new JFileChooser();

	@Override
	public void actionPerformed(ActionEvent sv) {
		// TODO: Implement NewDocument Action.

	}
}
