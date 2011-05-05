package vooga.debugger.view.grapher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * Core class for organizing framework for Grapher component of Vooga Debugger
 * 
 * @author Troy Ferrell
 */
public class DebuggerGrapher extends JFrame
{
	private GrapherView myGraphPanel;
	private GraphToolset myToolSet;
	
	private GraphFieldListPanel myGraphListPanel;
	
	public ArrayList<GraphGameField> myGraphFields = new ArrayList<GraphGameField>();
	
	public DebuggerGrapher()
	{
		this.setVisible(true);
		this.setSize(new Dimension(800, 500));
		this.setTitle("VOOGA Debugger Grapher");
		
		myGraphListPanel = new GraphFieldListPanel(this);
		
		myToolSet = new GraphToolset();
		
		myGraphPanel = new GrapherView(myToolSet);
		
		myToolSet.setGrapherPanel(myGraphPanel);
		
		JSplitPane horzSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		horzSplitPane.setResizeWeight(1.0);
		horzSplitPane.setTopComponent(myGraphPanel);
		horzSplitPane.setBottomComponent(myGraphListPanel);
		
		this.getContentPane().add(myToolSet, BorderLayout.NORTH);
		this.getContentPane().add(horzSplitPane, BorderLayout.CENTER);
	}
	
	public void repaint()
	{
		for(GraphGameField gf : myGraphFields)
			gf.updateView();
		
		super.repaint();
	}
	
	/**
	 * Add Field to Grapher
	 * @param gf
	 */
	public void addField(GraphGameField gf)
	{
		myGraphFields.add(gf);
		myGraphListPanel.addField(gf);
		myGraphPanel.addGraphData(gf.getGraphData());
		
		this.repaint();
	}
	
	/**
	 * Remove Field from Grapher
	 * @param gf
	 */
	public void removeField(GraphGameField gf)
	{
		myGraphFields.remove(gf);
		myGraphListPanel.removeField(gf);
		myGraphPanel.removeGraphData(gf.getGraphData());
		
		this.repaint();
	}
	
	
}
