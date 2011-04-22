package vooga.user.view.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.UIManager;



import vooga.user.controller.LoginController;
import vooga.user.main.ResourceManager;
import vooga.user.model.LoginTemplate;
import vooga.user.controller.*;
import vooga.user.view.actions.PasswordLogin;
import vooga.user.view.gui.middleFrame.SlogoMiddleFrame;
import vooga.user.voogauser.Display;

/**
 * This is the Actual GUI for the Picassa project - layout modeled after MATLAB - then adjusted for UserLogin.
 * 
 * @author Conrad Haynes
 * @author Ethan Yong-Hui Goh
 */

public class LoginViewer extends JFrame implements IView {
	private static final long serialVersionUID = 1L;
	private SlogoMiddleFrame middleFrame;
	private LoginController pc;

	public LoginViewer(String title, String prompt, Dimension dim,
			LoginController p) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		pc = p;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(title);
		this.setResizable(true);
		middleFrame = new SlogoMiddleFrame(pc, pc.getDefaultTemplate());
		this.add(middleFrame, BorderLayout.CENTER);
		this.setMinimumSize(dim);
		this.setVisible(true);
		this.update(new Display());
	}
	
	public LoginViewer(String title, String prompt, int dimx, int dimy,
			LoginController p) {
		this(title, prompt, new Dimension(dimx, dimy), p);
	}

	public void clear() {
		middleFrame.getImageCanvas().clear();
	}

	public void showError(String str) {
		JOptionPane.showMessageDialog(middleFrame, str);
	}
	
	/**
	 * This update method simply adds a new panel to the MiddleFrame in the GUI with the appropriate prompts.
	 * (much simplified from the previous Matlab or Picassa implementation) 
	 */
	@Override
	public void update(LoginTemplate[] template) {
		this.remove(middleFrame);
		this.add(new SlogoMiddleFrame(pc, template), BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void update(Display display) {}
}
