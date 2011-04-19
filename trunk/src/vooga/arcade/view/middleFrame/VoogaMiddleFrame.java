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

	private ResourceManager MiddleFrameResource = new ResourceManager(
			"vooga.arcade.resources.MiddleFrameResource");
	
	public VoogaMiddleFrame(PracticeController pc) {
		BorderLayout b = new BorderLayout();
		this.setLayout(b);
		rightPanel = new ColumnTextPanes(MiddleFrameResource
				.getInteger("RightPanelNumber"), MiddleFrameResource
				.getStringArray("RightPanelLabels"), pc);

		//this.add(middlePanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
	}
	
	public ColumnTextPanes getRightPanel()
	{
		return rightPanel;
	}

	//public Dimension getActiveCanvasSize() {
		//return middlePanel.getActiveCanvasSize();
	//}

//	public void addVariable(String str) {
//		rightPanel.addStringToComponent(MiddleFrameResource
//				.getInteger("VariablePanelIndex"), str);
//	}
//
//	public void setCurrentPlayer(String name) {
//		rightPanel.addString(MiddleFrameResource
//				.getInteger("CurrentPlayerIndex"), name);
//	}
//	
//	public void clearVariables() {
//		rightPanel.clearComponent(MiddleFrameResource
//				.getInteger("VariablePanelIndex"));
//	}
}
