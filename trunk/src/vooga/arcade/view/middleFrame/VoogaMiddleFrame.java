package vooga.arcade.view.middleFrame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import vooga.arcade.controller.ArcadeController;
import vooga.arcade.parser.ArcadeGameObject;
import vooga.arcade.view.gamePanel.ArcadePanelFactory;
import vooga.arcade.view.helper.ResourceManager;

/**
 * Connects the GUI Components to create the Middle Frame in the Vooga Project.
 * @author Andrea Scripa
 * @author Ethan Goh
 * @author KevinWang
 * 
 */
public class VoogaMiddleFrame extends JPanel
{
	private static final long serialVersionUID = 1L;

	private ThumbnailPanel middlePanel;
	private ColumnTextPanes rightPanel;

	private ResourceManager MiddleFrameResource = new ResourceManager(
			"vooga.arcade.resources.MiddleFrameResource");

	public VoogaMiddleFrame(ArcadeController pc)
	{
		BorderLayout b = new BorderLayout();
		this.setLayout(b);

		List<ArcadeGameObject> gameList = pc.queryModel("title", null);

		List<JPanel> allGames = ArcadePanelFactory
				.generateArcadeGamePanels(gameList);

		middlePanel = new ThumbnailPanel(allGames);
		rightPanel = new ColumnTextPanes(
				MiddleFrameResource.getInteger("RightPanelNumber"),
				MiddleFrameResource.getStringArray("RightPanelLabels"), pc);

		this.add(middlePanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}

	public ColumnTextPanes getRightPanel()
	{
		return rightPanel;
	}

	public void updateThumbnails(List<ArcadeGameObject> ao, String searchQuery)
	{
		middlePanel.addNewCard(ArcadePanelFactory
                .generateArcadeGamePanels(ao), searchQuery);
		middlePanel.show(searchQuery);
	}
}
