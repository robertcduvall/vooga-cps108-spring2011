package vooga.debugger.view;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import vooga.debugger.Debugger;
import vooga.debugger.model.DebuggerModel;
import vooga.debugger.model.GameField;

import vooga.debugger.util.*;

/**
 * @author Troy Ferrell
 * @author Ethan Goh
 */

public class DebuggerView extends JFrame 
{
	private GameTreePanel myGameTreePanel;
	
	private DebuggerContainer debuggerContainer;
	
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
		debuggerContainer = new DebuggerContainer(debug);
		
		JPanel toolbarPanel = makeToolbarPanel();
		
		this.add(toolbarPanel, BorderLayout.PAGE_START);
		this.add(debuggerContainer, BorderLayout.CENTER);
		
		this.setSize(new Dimension(600, 600));
		this.setTitle("VOOGA Debugger");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private JPanel makeToolbarPanel()
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
		
		return toolbarPanel; 
	}
	
	public void addFieldToView(GameField field)
	{
		debuggerContainer.addToLiveFields(field);
	}
	
	public void removeFieldFromView(GameField field)
	{
		debuggerContainer.removeFromLiveFields(field);
		debuggerContainer.revalidate();
		debuggerContainer.repaint();
	}
	
	public void println(String s)
	{
		debuggerContainer.println(s + "\n");
	}
}
