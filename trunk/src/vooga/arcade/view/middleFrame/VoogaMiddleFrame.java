package vooga.arcade.view.middleFrame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.helper.ResourceManager;

/**
 * Connects the GUI Components to create the Middle Frame in the Vooga Project.
 * 
 */
public class VoogaMiddleFrame extends JPanel{
	private static final long serialVersionUID = 1L;

	private ThumbnailPanel middlePanel;
	private ColumnTextPanes rightPanel;

	private ResourceManager MiddleFrameResource = new ResourceManager(
			"vooga.arcade.resources.MiddleFrameResource");
	
	public VoogaMiddleFrame(ArcadeController pc) {
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		
		ArrayList<JPanel> allGames = pc.queryModel(null);
		middlePanel = new ThumbnailPanel(allGames);
		rightPanel = new ColumnTextPanes(MiddleFrameResource
				.getInteger("RightPanelNumber"), MiddleFrameResource
				.getStringArray("RightPanelLabels"), pc);

		this.add(middlePanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}
	
	public ColumnTextPanes getRightPanel()
	{
		return rightPanel;
	}
	
	public void updateThumbnails(ThumbnailPanel newPanel)
	{
	    this.remove(middlePanel);
	    middlePanel = newPanel;
	    this.add(middlePanel, BorderLayout.CENTER);
	}
}
