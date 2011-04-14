package vooga.debugger.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import vooga.debugger.Debugger;
import vooga.debugger.model.GameField;

/**
 * @author Troy Ferrell
 * @author Ethan Goh
 */
public class DebuggerContainer extends JPanel 
{
	private GameTreePanel myGameTreePanel;
	private JPanel myLiveFieldsPanel;
	private JTextArea historyArea;
	private JScrollPane scrollPane;
	
	private static final int DEFAULT_TEXT_FIELD_WIDTH = 90;
	private static final int DEFAULT_TEXT_FIELD_HEIGHT = 6;

	
	// Toolbar buttons
	public JButton playButton;
	public JButton stopButton;
	public JButton restartButton;
	
	public DebuggerContainer(Debugger debug)
	{
		myGameTreePanel = new GameTreePanel(debug);
		myLiveFieldsPanel = new JPanel();
		myLiveFieldsPanel.setLayout(new BoxLayout(myLiveFieldsPanel, BoxLayout.PAGE_AXIS));
		
		historyArea = new JTextArea(DEFAULT_TEXT_FIELD_HEIGHT, DEFAULT_TEXT_FIELD_WIDTH);
		historyArea.setEditable(false);
		historyArea.setLineWrap(true);
		historyArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(historyArea);

		this.setLayout(new GridLayout(1,3));
		this.add(myGameTreePanel.getPane());
		this.add(myLiveFieldsPanel);
		this.add(scrollPane);
		this.setSize(new Dimension(600, 600));
		this.setVisible(true);
	}

	public void println(String s)
	{
		historyArea.append(s + "\n");
	}

	public void addToLiveFields(GameField field)
	{
		myLiveFieldsPanel.add(field);
	}
	
	public void removeFromLiveFields(GameField field)
	{
		myLiveFieldsPanel.remove(field);
	}
}
