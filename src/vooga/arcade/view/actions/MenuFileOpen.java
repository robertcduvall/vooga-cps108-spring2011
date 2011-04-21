package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import vooga.arcade.controller.IController;
import vooga.arcade.controller.ArcadeController;

/**
 * The action associated with clicking the File -> Open menu bar. Opens a
 * particular file that could represent a history of data or a previously stored
 * expression.
 * 
 * 
 */
public class MenuFileOpen extends AbstractVoogaAction {

	ArcadeController pc = new ArcadeController();

	public MenuFileOpen(IController p) {
		super(p);
	}

	private static final JFileChooser fc = new JFileChooser();

	@Override
	public void actionPerformed(ActionEvent sv) {
		int chosen = fc.showOpenDialog(null);
		if (chosen == JFileChooser.APPROVE_OPTION) {
			File open = fc.getSelectedFile();
		}
	}
}
