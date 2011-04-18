package vooga.arcade.view.middleFrame;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import vooga.arcade.controller.PracticeController;
import vooga.arcade.view.helper.ResourceManager;

/**
 * Connects the GUI Components to create the Middle Frame in the Vooga Project.
 * 
 */
public class VoogaMiddleFrame extends JPanel{
	private static final long serialVersionUID = 1L;

	//private VariableTabbedPane middlePanel;
	private ColumnTextPanes rightPanel;

	private ResourceManager slogoMiddleFrameResource = new ResourceManager(
			"SlogoMiddleFrameResource");
	
	public VoogaMiddleFrame(PracticeController pc) {
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		//middlePanel = new VariableTabbedPane(slogoMiddleFrameResource
				//.getInteger("MiddlePanelInitialTabs"));
		rightPanel = new ColumnTextPanes(slogoMiddleFrameResource
				.getInteger("RightPanelNumber"), slogoMiddleFrameResource
				.getStringArray("RightPanelLabels"), pc);

		//this.add(middlePanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}

	//public ImageCanvas getImageCanvas() {
		//return middlePanel.getActiveCanvas();
	//}
	
	public ColumnTextPanes getRightPanel()
	{
		return rightPanel;
	}

	//public Dimension getActiveCanvasSize() {
		//return middlePanel.getActiveCanvasSize();
	//}

	public void addVariable(String str) {
		rightPanel.addStringToComponent(slogoMiddleFrameResource
				.getInteger("VariablePanelIndex"), str);
	}

	public void setCurrentPlayer(String name) {
		rightPanel.addString(slogoMiddleFrameResource
				.getInteger("CurrentPlayerIndex"), name);
	}
	
	public void clearVariables() {
		rightPanel.clearComponent(slogoMiddleFrameResource
				.getInteger("VariablePanelIndex"));
	}
}
