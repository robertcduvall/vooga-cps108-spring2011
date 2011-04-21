package vooga.arcade.view.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import vooga.arcade.controller.ArcadeController;


/**
 * The action associated with clicking the File -> Save menu bar. Saves the
 * current picture as an image.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */

public class MenuFileSave extends AbstractVoogaAction {
	public MenuFileSave(ArcadeController p) {
		super(p);
	}

	protected static JFileChooser saveFileChooser = new JFileChooser(System
			.getProperties().getProperty("user.dir"));

	@Override
	public void actionPerformed(ActionEvent e) {
		int retval = saveFileChooser.showSaveDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File file = saveFileChooser.getSelectedFile();
			try {
				PrintWriter pw = new PrintWriter(file, "UTF8");
				// Get raw data and save it.

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}
		}

	}
}
