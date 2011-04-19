package vooga.leveleditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The menu bar for the level editor.
 */
public class MenuBar extends JMenuBar
{

    public MenuBar()
    {
        super();

        /*
         * Set up the File menu.
         */
        JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);
        this.add(filemenu);

        JMenuItem newmenuitem = new JMenuItem("New");
        newmenuitem.setMnemonic(KeyEvent.VK_N);
        newmenuitem.addActionListener(new NewAction());
        filemenu.add(newmenuitem);

        JMenuItem loadmenuitem = new JMenuItem("Load");
        loadmenuitem.setMnemonic(KeyEvent.VK_L);
        loadmenuitem.addActionListener(new LoadAction());
        filemenu.add(loadmenuitem);

        JMenuItem savemenuitem = new JMenuItem("Save");
        savemenuitem.setMnemonic(KeyEvent.VK_S);
        savemenuitem.addActionListener(new SaveAction());
        filemenu.add(savemenuitem);

        JMenuItem exitmenuitem = new JMenuItem("Exit");
        exitmenuitem.setMnemonic(KeyEvent.VK_X);
        exitmenuitem.addActionListener(new ExitAction());
        filemenu.add(exitmenuitem);
    }

    private class NewAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    private class LoadAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    private class SaveAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    private class ExitAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

}
