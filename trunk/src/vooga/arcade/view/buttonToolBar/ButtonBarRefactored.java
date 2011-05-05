package vooga.arcade.view.buttonToolBar;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import vooga.arcade.controller.ArcadeController;
import vooga.arcade.view.actions.ActionFactory;
import vooga.arcade.view.helper.ResourceManager;

/**
 * Refactor ButtonBar file
 * @author Chao Chen
 *
 */

public class ButtonBarRefactored implements Commons
{
    /**
     * refactored to remove hard-coded string, using interface
     */
    private static ResourceManager buttonBarResource = new ResourceManager(TOOLBAR_MANAGER);
    private static String[] toolBarEntriesImageNames = buttonBarResource.getStringArray(TOOLBAR);
    private static ArcadeController c;

    public static JToolBar createButtonToolBar(ArcadeController p, String[] entryNames) 
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        //refactored to remove hard-coded string
        for (int k=0; k < entryNames.length; k++) 
        {   //refactored to remove if-else tree
            String buttonName = k==0 && entryNames[k].equals(LOGIN) ? buttonBarResource.getString(entryNames[k]) : entryNames[k];
            Icon buttonIcon = new ImageIcon(SRC + buttonName);
            ButtonBarItem b = (k==0) && !(entryNames[k].equals(LOGIN)) ? new ButtonBarItem(new ImageIcon(), USER, p) : new ButtonBarItem(buttonIcon, entryNames[k], p);
            toolBar.add(b);
            if (k < entryNames.length-1) toolBar.addSeparator(new Dimension(400, 1));
        }
        
        JTextField searchField = new JTextField();
        searchField.addActionListener(ActionFactory.createAction(BUTTONBAR_SEARCHACTION, p));
        toolBar.add(searchField);
        return toolBar;
    }
    
    public static JToolBar createButtonToolBar(ArcadeController p)
    {
        return ButtonBar.createButtonToolBar(p, toolBarEntriesImageNames);
    }
    
    public static void swapButtons(String username)
    {
        if (username.equals("Login"))
        {
            toolBarEntriesImageNames = buttonBarResource.getStringArray("ToolBar");
            JToolBar newBar = createButtonToolBar(c);
            c.updateButtonBar(newBar);     
        }
        else{
            String[] entryNames = toolBarEntriesImageNames;
            entryNames[0] = username;
            toolBarEntriesImageNames = entryNames;
            JToolBar newBar = createButtonToolBar(c, entryNames);
            c.updateButtonBar(newBar);
        }
    }
}