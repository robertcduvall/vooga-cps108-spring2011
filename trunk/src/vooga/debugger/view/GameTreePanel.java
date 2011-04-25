
package vooga.debugger.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.golden.gamedev.Game;

import vooga.debugger.Debugger;


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
		JMenuItem addGameFieldItem = new JMenuItem("Scope game field");
		addGameFieldItem.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myDebugger.scopeField((GameTreeNode)selectedPath.getLastPathComponent());
			}
		}); 
		myTreePopup.add( addGameFieldItem );
		
		JMenuItem recordGameFieldItem = new JMenuItem("Record game field");
		recordGameFieldItem.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myDebugger.recordField((GameTreeNode)selectedPath.getLastPathComponent());
			}
		}); 
		myTreePopup.add( recordGameFieldItem );
		
		JMenuItem showDefaultFields = new JMenuItem("Show Default Fields");
		showDefaultFields.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myDebugger.setViewPreference((GameTreeNode)selectedPath.getLastPathComponent(),"");
			}
		}); 
		myTreePopup.add( showDefaultFields );
		
		JMenuItem showDeclaredFields = new JMenuItem("Show Declared Fields");
		showDeclaredFields.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myDebugger.setViewPreference((GameTreeNode)selectedPath.getLastPathComponent(),"declared");
			}
		}); 
		myTreePopup.add( showDeclaredFields );
		
		JMenuItem showAllFields = new JMenuItem("Show All Fields");
		showAllFields.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				myDebugger.setViewPreference((GameTreeNode)selectedPath.getLastPathComponent(),"all");
			}
		}); 
		myTreePopup.add( showAllFields );
	}
	
	private void initTree()
	{
		myRootNode = myDebugger.getGameTree();
		myGameTree = new JTree(myRootNode);
		myGameTree.addMouseListener(new GameTree_MouseAdapter());
		myGameTree.addTreeExpansionListener(new GameTree_ExpansionListener());
		selectedPath = myGameTree.getPathForRow(0);
		
		myGameTree.setCellRenderer(new GameTreeCellRenderer());
	}
	
	public JScrollPane getPane()
	{
		return myGameTreePane;
	}
	
	public JTree getTree()
	{
		return myGameTree;
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
	 * @author Austin Benesh
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
	        	 
	        	 if(e.getButton() == e.BUTTON3) // if right click
	        	 {
	        		 GameTreeNode treeNode = (GameTreeNode)selectedPath.getLastPathComponent();
	        		 Field nodeField = (Field)treeNode.getUserObject();
	        		 if(!nodeField.getType().equals(Game.class))
	        			 myTreePopup.show(e.getComponent(), e.getX(), e.getY());
	        	 }
	        	 else if(e.getClickCount() == 2) // if double click
	             { 
	        		 GameTreeNode treeNode = (GameTreeNode)selectedPath.getLastPathComponent();
	        		 Field nodeField = (Field)treeNode.getUserObject();
	        		 if(!nodeField.getType().equals(Game.class))
	        			 myDebugger.scopeField((GameTreeNode)selectedPath.getLastPathComponent());
	             }
	         }
	     }
	}
	
	private class GameTree_ExpansionListener implements TreeExpansionListener
	{
		@Override
		public void treeCollapsed(TreeExpansionEvent arg0){}

		@Override
		public void treeExpanded(TreeExpansionEvent e)
		{
			GameTreeNode treeNode = (GameTreeNode)e.getPath().getLastPathComponent();
			Field nodeField = (Field)treeNode.getUserObject();
			if(!nodeField.getType().equals(Game.class))
			{
				myDebugger.myModel.getFields(treeNode.getField().getType(), treeNode, myDebugger.showAll, true);
				DefaultTreeModel treeModel = (DefaultTreeModel)myGameTree.getModel();
				treeModel.reload(treeNode);
			}
		}
	}
}
