
package vooga.debugger.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import vooga.debugger.Debugger;

import vooga.debugger.util.MethodAction;


/**
 * Panel element of Debugger View that controls initialization and modification of GUI components for Game Tree
 * 
 * @author Troy Ferrell
 */
public class GameTreePanel
{
	private Debugger myDebugger;
	
	private JTree myGameTree;
	private GameTreeNode myRootNode;
	private TreePath selectedPath;
	
	private JPopupMenu myTreePopup;
	
	private JScrollPane myGameTreePane;
	
	public GameTreePanel(Debugger debug)
	{
		myDebugger = debug;
		
		initTree();
		initPopup();
		
		myGameTreePane = new JScrollPane(myGameTree);
	}

	private void initPopup()
	{
		myTreePopup = new JPopupMenu();
		JMenuItem addGameFieldItem = new JMenuItem("Add game field");
		addGameFieldItem.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myDebugger.addField((GameTreeNode)selectedPath.getLastPathComponent());
			}
		}); 
		myTreePopup.add( addGameFieldItem );
		
		myTreePopup.add( new JMenuItem("Record game field") );
	}
	
	private void initTree()
	{
		myRootNode = myDebugger.getGameTree();
		myGameTree = new JTree(myRootNode);
		myGameTree.addMouseListener(new GameTree_MouseAdapter());
		selectedPath = myGameTree.getPathForRow(0);
		
		myGameTree.setCellRenderer(new GameTreeCellRenderer());
	}
	
	public JScrollPane getPane()
	{
		return myGameTreePane;
	}
	
	/**
	 * Update the Game Tree to redisplay any new data
	 */
	public void updateTree()
	{
		DefaultTreeModel treeModel = (DefaultTreeModel)myGameTree.getModel();
		myRootNode = myDebugger.getGameTree();
		treeModel.setRoot(myRootNode);
		treeModel.reload();
	}
	
	/**
	 * Class used to handle mouse input for Game Tree
	 * @author Troy Ferrell
	 */
	private class GameTree_MouseAdapter extends MouseAdapter
	{
		public void mousePressed(MouseEvent e) 
	     {
	         int selRow = myGameTree.getRowForLocation(e.getX(), e.getY());
	         selectedPath = myGameTree.getPathForLocation(e.getX(), e.getY());
	         if(selRow != -1) 
	         {
	        	 myGameTree.setSelectionPath(selectedPath);
	        	 
	        	 if(e.getButton() == e.BUTTON3)
	        	 {
	        		 myTreePopup.show(e.getComponent(), e.getX(), e.getY());
	        	 }
	        	 else if(e.getClickCount() == 2) 
	             { 
	            	 DefaultMutableTreeNode node = (DefaultMutableTreeNode)selectedPath.getLastPathComponent();
	            	 if(node.isLeaf())
	            		 myDebugger.addField((GameTreeNode)selectedPath.getLastPathComponent());
	             }
	         }
	     }// end method
		
	}// end class

}
