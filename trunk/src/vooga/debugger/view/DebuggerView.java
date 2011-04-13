package vooga.debugger.view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import vooga.debugger.Debugger;
import vooga.debugger.model.DebuggerModel;
import vooga.debugger.model.GameField;
import vooga.debugger.util.MethodAction;

/**
 * Core class in charge of the view components for the Debugger system
 * 
 * @author Troy Ferrell
 */
public class DebuggerView extends JFrame 
{
	private GameTreePanel myGameTreePanel;
	private JPanel myLiveFieldsPanel;
	
	// Toolbar buttons
	public JButton playButton;
	public JButton stopButton;
	public JButton restartButton;
	
	private Debugger myDebugger;
	private DebuggerModel myModel;
	
	public DebuggerView(Debugger debug, DebuggerModel model)
	{
		myDebugger = debug;
		myModel = model;
		
		addToolbar();
		
		myGameTreePanel = new GameTreePanel(debug);
		
		myLiveFieldsPanel = new JPanel();
		myLiveFieldsPanel.setLayout(new BoxLayout(myLiveFieldsPanel, BoxLayout.PAGE_AXIS));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setResizeWeight(0.4);
		splitPane.setTopComponent(myGameTreePanel.getPane());
		splitPane.setBottomComponent(myLiveFieldsPanel);
		
		this.getContentPane().add(splitPane);
		this.setSize(new Dimension(600, 600));
		this.setTitle("VOOGA Debugger");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void addToolbar()
	{
		JToolBar toolBar = new JToolBar();
		
		playButton = new JButton(new ImageIcon("src/resources/Debugger_PlayButton.png"));
		playButton.addActionListener(new MethodAction(myDebugger, "playGame"));
		playButton.setEnabled(false);
		toolBar.add(playButton );
		
		stopButton = new JButton(new ImageIcon("src/resources/Debugger_StopButton.png"));
		stopButton.addActionListener(new MethodAction(myDebugger, "stopGame"));
		toolBar.add(stopButton );
		
		restartButton = new JButton(new ImageIcon("src/resources/Debugger_RestartButton.png"));
		restartButton.addActionListener(new MethodAction(myDebugger, "restartGame"));
		toolBar.add(restartButton );
		
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.add(toolBar);
		toolbarPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		this.getContentPane().add( toolbarPanel, BorderLayout.NORTH);
	}
	
	public void addFieldToView(GameField field)
	{
		this.myLiveFieldsPanel.add(field);
	}
	
	public void removeFieldFromView(GameField field)
	{
		this.myLiveFieldsPanel.remove(field);
		this.myLiveFieldsPanel.revalidate();
		this.myLiveFieldsPanel.repaint();
	}
	
}
