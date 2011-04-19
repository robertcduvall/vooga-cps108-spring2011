package vooga.leveleditor.gui;

import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * The main controller.
 */
public class SwingGUI extends JFrame
{

    private JMenuBar menubar;

    private JToolBar toolbar;

    private JLabel statusbar;

    public SwingGUI()
    {
        /*
         * Initialize the window.
         */
        setTitle("Level Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * Set up the menubar.
         */
        this.setJMenuBar(new MenuBar());

        /*
         * Set up the toolbar.
         */
        JToolBar toolbar = new ToolBar();
        this.add(toolbar, BorderLayout.NORTH);

        /*
         * Set up the drawing board, which is the main area of action.
         */
        DrawingBoard db = new DrawingBoard(this);
        add(db, BorderLayout.CENTER);

        /*
         * Set up the status bar.
         */
        this.statusbar = new StatusBar();
        this.add(statusbar, BorderLayout.SOUTH);
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

    public void setStatusBar(String message)
    {
        statusbar.setText(message);
    }

    public static void main(String[] args)
    {
        SwingGUI g = new SwingGUI();

        g.pack();
        g.setVisible(true);
        g.setExtendedState(g.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

}
