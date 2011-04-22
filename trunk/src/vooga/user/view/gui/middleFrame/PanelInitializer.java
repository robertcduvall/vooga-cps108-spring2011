package vooga.user.view.gui.middleFrame;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vooga.user.view.gui.ImageCanvas;
import vooga.user.main.*;
import vooga.user.controller.*;
import vooga.user.model.*;

/**
 * A generic component that can start with a specified number of tabs, and has a
 * Add New Tab button that would dynamically change the size of the tab.
 * 
 * @author Conrad 
 * 
 */
public class PanelInitializer extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private ResourceManager guiResource = new ResourceManager("vooga.user.resources.GUIResource");
	private LoginTemplate[] loginInfo;

	/**
	 * This default constructor for the panelInitializer basical sets up the Gui display and layout - 
	 * through adding all the components in
	 */
	public PanelInitializer(ILoginController controller,LoginTemplate[] construct) {
		loginInfo = construct;
		this.setPreferredSize(new Dimension(640, 480));
		this.setLayout(new GridLayout());
		this.add(new ImageCanvas(guiResource.getString("DefaultSplashImage")));
		FieldPanel field = new FieldPanel(loginInfo, controller);
		this.add(field);
		this.add(new ImageCanvas(guiResource.getString("DefaultSplashImage")));
	}

	public PanelInitializer(Dimension d) {
		this.setPreferredSize(d);
	}

	public ImageCanvas getActiveCanvas() {
		return (ImageCanvas) tabbedPane.getSelectedComponent();
	}

	public Dimension getActiveCanvasSize() {
		return tabbedPane.getSize();
	}
}