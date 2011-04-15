package vooga.debugger.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import vooga.debugger.util.Reflection;




/**
 * The menubar for the Debugger System
 * 
 * @author Troy Ferrell
 */

public class DebuggerMenuBar extends JMenuBar 
{
	private static final String SCANNER_DELIMITTER = "\\s+";
	
	public DebuggerMenuBar(String resourceFile)
	{
		initMenuBar(resourceFile);
	}
	
	/**
	 * Use resource file to create menus for menubar
	 */
	private void initMenuBar(String resourceFile) 
	{
		try
		{
			Scanner reader = new Scanner(new File(resourceFile));
			
			while(reader.hasNext())
			{
				String menu = reader.next();
				this.add(createMenu(menu, reader.next()));
			}
		
		}catch(FileNotFoundException e)
		{
			// TODO: notify prompt	
		}
	}
	
	/**
	 * Create Menu for menu bar, use resource file to get menu items
	 * - menuitem actions point to methods in controller classes using MethodAction class
	 * 	 and reflection
	 * 
	 * @param title of menu
	 * @param resourceFile - file that holds menus properties
	 */
	public JMenu createMenu(String title, String resourceFile)
	{
		JMenu newMenu = new JMenu(title);
		
		try
		{
			Scanner reader = new Scanner(new File(resourceFile)).useDelimiter(SCANNER_DELIMITTER);
			
			while(reader.hasNext())
			{
				String menuType = reader.next();
				String menuItemTitle = reader.next();
				String actionMethod = reader.next();
				String controllerClassName = reader.next();
				String targetComponentName = reader.next();
				
				//AbstractController controller = myController.getControllerFor(controllerClassName);
				//JComponent target = myGUI.getSLogoComponent(targetComponentName);
				
				JMenuItem newMenuItem = new JMenuItem(menuItemTitle);//(JMenuItem)Reflection.createInstance(, menuItemTitle);
				
				//newMenuItem.addActionListener( new MethodAction(Class.forName(controllerClassName).cast(controller),
				//		actionMethod, Class.forName(targetComponentName).cast( target )));
				
				newMenu.add( newMenuItem );
			}
		}catch(FileNotFoundException e)
		{
			// TODO: notify prompt
		}
		//catch(ClassNotFoundException e) {}
		
		return newMenu;
	}

	 
}
