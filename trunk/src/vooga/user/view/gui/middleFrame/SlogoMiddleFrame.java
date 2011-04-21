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
 * Connects the GUI Components to create the Middle Frame in the Slogo Project.
 * 
 * @author Ethan Yong-Hui Goh
 * 
 */
public class SlogoMiddleFrame extends JPanel{
	private static final long serialVersionUID = 1L;
	private PanelInitializer middlePanel;
	private LoginTemplate[] log;
	
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
