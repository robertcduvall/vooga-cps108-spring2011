
package vooga.debugger.view.grapher;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import vooga.debugger.util.MethodAction;



/**
 * Component class that acts as toolbar for Vooga Grapher. 
 * Allow user to one specific tool at a time for viewing manipulation(ie translating, zooming, etc)
 * 
 * @author Troy
 */
public class GraphToolset extends JToolBar 
{
	public static final int HAND_TOOL = 0; 
	
	public static final int TRANSLATE_TOOL = 1;
	
	public static final int ZOOM_IN_TOOL= 2;
	public static final int ZOOM_OUT_TOOL= 3;
	
	public static final int ZOOM_OUT_Y_TOOL= 4;
	public static final int ZOOM_IN_Y_TOOL= 5;
	
	public static final int ZOOM_OUT_X_TOOL= 6;
	public static final int ZOOM_IN_X_TOOL= 7;
	
	private int selectedTool = HAND_TOOL;
	
	private ArrayList<JButton> toolButtons;
	
	private GrapherPanel myParentPanel;
	
	public GraphToolset()
	{
		toolButtons = new ArrayList<JButton>();
		
		// TODO: need to put these values into resource file 
		String [] toolImgs = { "src/vooga/debugger/resources/toolset/HandTool", "src/vooga/debugger/resources/toolset/TranslateTool", 
				"src/vooga/debugger/resources/toolset/ZoomInTool", "src/vooga/debugger/resources/toolset/ZoomOutTool", 
				"src/vooga/debugger/resources/toolset/CollapseYToolset", "src/vooga/debugger/resources/toolset/ExpandYToolset",
				"src/vooga/debugger/resources/toolset/CollapseXToolset", "src/vooga/debugger/resources/toolset/ExpandXToolset"};
		
		for(int i = 0; i < toolImgs.length; i++)
		{
			JButton toolButton = new JButton(new ImageIcon(toolImgs[i] + ".png"));
			toolButton.setSelectedIcon(new ImageIcon(toolImgs[i] + "_Selected.png"));
			toolButton.addActionListener(new MethodAction(this, "toolSelected", toolButton));
			toolButtons.add(toolButton);
			this.add(toolButton);
		}
		
		toolButtons.get(0).setSelected(true);
	}
	
	/**
	 * Pass in the grapher canvas for the toolset to reference.
	 * @param gp
	 */
	public void setGrapherPanel(GrapherPanel gp)
	{
		// Note: has to be public for DebuggerGrapher class to access
		this.myParentPanel = gp;
	}
	
	/**
	 * Action Event Method - called when one of tool buttons are pressed. 
	 * Set clicked tool as selected and deselects all other tools
	 * @param button
	 */
	public void toolSelected(JButton button)
	{
		button.setSelected(true);
		for(int i = 0; i < toolButtons.size(); i++)
		{
			if(!toolButtons.get(i).equals(button))
				toolButtons.get(i).setSelected(false);
			else
				selectedTool = i;
		}
	
		// TODO: yes, I know if structure is ugly and bad design...need to at least store in array and read from file
		
		// Change cursor of panel
		Cursor newCursor = Cursor.getDefaultCursor();
		
		if(selectedTool == GraphToolset.HAND_TOOL)
		{
			newCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		}
		else if(selectedTool == GraphToolset.TRANSLATE_TOOL)
		{
			newCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		}
		else if(selectedTool == GraphToolset.ZOOM_IN_TOOL)
		{	
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			newCursor = toolkit.createCustomCursor(toolkit.getImage("src/resources/toolset/ZoomInCursor.png") , new Point(0,0), "Zoom In");
		}
		else if(selectedTool == GraphToolset.ZOOM_OUT_TOOL)
		{
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			newCursor = toolkit.createCustomCursor(toolkit.getImage("src/resources/toolset/ZoomOutCursor.png") , new Point(0,0), "Zoom Out");
		}
		else if(selectedTool == GraphToolset.ZOOM_IN_Y_TOOL)
		{
			newCursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
		}
		else if(selectedTool == GraphToolset.ZOOM_OUT_Y_TOOL)
		{
			newCursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
		}
		else if(selectedTool == GraphToolset.ZOOM_IN_X_TOOL)
		{
			newCursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
		}
		else if(selectedTool == GraphToolset.ZOOM_OUT_X_TOOL)
		{
			newCursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
		}
		
		if(myParentPanel != null)
			myParentPanel.setCursor(newCursor);
	}
	
	/**
	 * Get tool currently selected by user
	 * @return
	 */
	public int getSelectedTool()
	{
		return selectedTool;
	}
}
