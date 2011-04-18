package vooga.leveleditor.gui;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class SwingGUI extends JFrame
{

    //private XMLDealer xml;

    //private ScrollablePicture picture;

    private JMenuBar menubar;

    private JToolBar toolbar;

    private StatusBar statusbar;

    public SwingGUI()
    {
        /*
         * Initialize the window.
         */
        setTitle("Level Editor");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Set up the menubar.
         */
        setupMenubar();

        /*
         * Set up the toolbar. 
         */
        setupToolbar();

        /*
         * Set up the drawing board, which is the main area of action.
         */
        DrawingBoard db = new DrawingBoard(this);
        add(db, BorderLayout.CENTER);

        /*
         * Set up the status bar.
         */
        setupStatusbar();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Helper method for the constructor. Sets up the menubar.
     */
    private void setupMenubar()
    {
        menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);
        menubar.add(filemenu);

        JMenuItem newmenuitem = new JMenuItem("New");
        newmenuitem.setMnemonic(KeyEvent.VK_N);
        newmenuitem.addActionListener(new NewAction());
        filemenu.add(newmenuitem);

        JMenuItem loadmenuitem = new JMenuItem("Load");
        loadmenuitem.setMnemonic(KeyEvent.VK_L);
        // loadmenuitem.addActionListener(new LoadAction());
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

    /*
     * The following are all helper classes for the menubar.
     */

    private class NewAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

        }
    }
    /**
    private class LoadAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fc = new JFileChooser();
            int val = fc.showOpenDialog(SwingGUI.this);
            if(val == JFileChooser.APPROVE_OPTION)
            {
                xml = new XMLDealer(fc.getSelectedFile());
                System.out.println(xml.toString());
            }
        }
    }
     */
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

    /**
     * Helper method for the constructor. Sets up the toolbar.
     */
    private void setupToolbar()
    {
        toolbar = new JToolBar();
        add(toolbar, BorderLayout.NORTH);

        JButton arrowButton = new JButton("Arrow");
        toolbar.add(arrowButton);

        JButton grabButton = new JButton("HandScroll");
        toolbar.add(grabButton);
    }

    private void setupStatusbar()
    {
        statusbar = new StatusBar();
        statusbar.setBorder(BorderFactory.createEtchedBorder(
                EtchedBorder.RAISED));
        add(statusbar, BorderLayout.SOUTH);
    }

    /**
     * The status bar on the bottom of the window. Displays helpful messages
     * to the user, including the mouse coordinates.
     */
    private class StatusBar extends JLabel
    {
        public StatusBar()
        {
            super();
            setText("Ready");
        }
    }

    protected void setStatusBar(String message)
    {
        statusbar.setText(message);
    }

    public static void main(String[] args)
    {
        SwingGUI g = new SwingGUI();
    }

}
