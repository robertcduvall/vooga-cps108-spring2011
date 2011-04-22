package vooga.user.view.gui.middleFrame;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import vooga.user.view.gui.ImageCanvas;
import vooga.user.main.*;
import vooga.user.controller.*;
import vooga.user.model.*;
import vooga.user.view.gui.*;


/**
 * Connects the GUI Components to create the Middle Frame in the UserLogin Project.
 * Note this class, although very much simplified, can easily be expanded
 * @author Conrad Haynes, Ethan Yong-Hui Goh
 */
public class SlogoMiddleFrame extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelInitializer middlePanel;
	private LoginTemplate[] log;
	
	/**
	 * This simplified method simply constructs a middleFrame in the GUI from which we create our (Login)Panel
	 */
	public SlogoMiddleFrame(LoginController pc, LoginTemplate[] construct) {
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		log = construct;
		middlePanel = new PanelInitializer(pc, log);
		this.add(middlePanel, BorderLayout.CENTER);
	}

	public ImageCanvas getImageCanvas() {
		return middlePanel.getActiveCanvas();
	}
}
