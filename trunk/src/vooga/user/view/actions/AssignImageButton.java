package vooga.user.view.actions;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import vooga.user.controller.ILoginController;
import vooga.user.view.gui.middleFrame.FieldPanel;


public class AssignImageButton extends AbstractLoginAction{

	FieldPanel panel;
	public AssignImageButton(ILoginController p, FieldPanel fieldPanel) {
		super(p);
		controller = p;
		panel = fieldPanel;
	}

	protected static JFileChooser imageFileChooser = new JFileChooser(System
			.getProperties().getProperty("user.dir"));
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int retval = imageFileChooser.showSaveDialog(null);
		if (retval == JFileChooser.APPROVE_OPTION) {
			File file = imageFileChooser.getSelectedFile();
		Image image = null;
		try {
		    // Read from a file
		    image = ImageIO.read(file).getScaledInstance(100, 100, 1);
		    controller.getVooga().setIcon(image);
		} catch (IOException e) {controller.displayError("Incorrect File Type, Please select an Image");
		}
		JLabel userImage = new JLabel(new ImageIcon(image));
		panel.remove(2);
		panel.add(userImage,2);
		panel.setVisible(false);
		panel.setVisible(true);
		controller.getVooga().setIcon(image);
		}
	}
}