
package vooga.debugger.view.grapher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * @author Troy Ferrell
 */
public class GrapherFrame extends JFrame 
{
	private static final String SCANNER_DELIMITTER = "\\s+";
	
	// TODO: this class should be destroyed.
	// Better extension permitting time would be to create JTabbedPane in original debugger grapher
	// One tab would also be connected to debugger while other tabs would be saved data 
	
	private GrapherView myGraphPanel;
	private GraphToolset myToolSet;
	
	public GrapherFrame(File dataFile)
	{
		this.setSize(new Dimension(800, 500));
		this.setTitle("Graphing Frame");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		myToolSet = new GraphToolset();
		
		myGraphPanel = new GrapherView(myToolSet);
		myGraphPanel.addGraphData(getSavedField(dataFile));
		
		myToolSet.setGrapherPanel(myGraphPanel);
		
		JSplitPane horzSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		horzSplitPane.setResizeWeight(0.0);
		horzSplitPane.setTopComponent(myToolSet);
		horzSplitPane.setBottomComponent(myGraphPanel);
		
		
		this.getContentPane().add(horzSplitPane);
	}
	
	private GraphData getSavedField(File dataFile)
	{
		GraphData gd = new GraphData();
		
		try
		{
			Scanner reader = new Scanner(dataFile).useDelimiter(SCANNER_DELIMITTER);
			gd.setColor(Color.getColor(reader.next()));
			
			while(reader.hasNext())
			{		
				double x = reader.nextDouble();
				double y = reader.nextDouble();
				gd.addData(x, y);
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("NO!");
			e.printStackTrace();
		}
		
		return gd;
	}
}
