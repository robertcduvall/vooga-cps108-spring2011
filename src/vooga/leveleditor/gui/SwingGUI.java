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
         * Set up the drawing board, which is the main area of action.
         */
        DrawingBoard db = new DrawingBoard(this);
        add(db, BorderLayout.CENTER);
    }


}
