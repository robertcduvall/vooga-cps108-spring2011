package vooga.debugger.view.grapher;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * WIP class - going to be used for recording values over time in game and displaying graphically
 * 
 * @author Troy Ferrell
 */
public class DebuggerGrapher extends JFrame
{
	// private JPanel myCanvas;
	 
	private ArrayList<GraphField> myGraphFields = new ArrayList<GraphField>();
	
	private GraphPanel myGraphPanel;
	
	private JPanel myGraphListPanel;
	private JPanel myFunctionPanel;
	
	public DebuggerGrapher()
	{
		initFunctionPanel();
		
		myGraphListPanel = new JPanel();
		
		myGraphPanel = new GraphPanel();
		
		JSplitPane sideVerticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sideVerticalSplitPane.setTopComponent(myGraphListPanel);
		sideVerticalSplitPane.setBottomComponent(myFunctionPanel);
		
		JSplitPane horzSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		horzSplitPane.setTopComponent(myGraphPanel);
		horzSplitPane.setBottomComponent(sideVerticalSplitPane);
		
		this.setVisible(true);
	}
	
	public void initFunctionPanel()
	{
		myFunctionPanel = new JPanel();
		myFunctionPanel.setLayout(new GridLayout(2,2));
		
		JButton selectAllButton = new JButton("Select All");
		selectAllButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				for(GraphField gf : myGraphFields)
					gf.setSelected(true);
			}
		});
		myFunctionPanel.add(selectAllButton);
		
		JButton deselectAllButton = new JButton("Deselect All");
		deselectAllButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				for(GraphField gf : myGraphFields)
					gf.setSelected(false);
			}
		});
		myFunctionPanel.add(deselectAllButton);
		
		
		JButton zoomInButton = new JButton("Zoom IN");
		zoomInButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myGraphPanel.zoomIn();
			}
		});
		myFunctionPanel.add(zoomInButton);
		
		JButton zoomOutButton = new JButton("Zoom Out");
		zoomOutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myGraphPanel.zoomOut();
			}
		});
		myFunctionPanel.add(zoomOutButton);
	}
	
	public void udpate(long deltaTime)
	{		
		// handle recording here for max time
		
		super.repaint();
	}

	
	/**
	 * Method in charge of drawing scene onto JPanel(aka canvas)
	 * 
	 * @param g - Graphics object passed by java system to help with drawing
	 */
	public void paint(Graphics g)
	{
		// draw lines
		
		for(GraphField gf : myGraphFields)
		{
			if(gf.isSelected())
			{
				
				
			}
		}
	}
	
	public static void main(String [] args)
	{
		DebuggerGrapher grapher = new DebuggerGrapher();
		grapher.setVisible(true);
		
		
	}
}
