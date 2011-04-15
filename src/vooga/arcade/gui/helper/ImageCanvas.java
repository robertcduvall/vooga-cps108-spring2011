package vooga.arcade.gui.helper;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import vooga.arcade.gui.drawTools.Display;

/**
 * A container that contains all the information associated with the Image
 * displayed on the screen.
 *@author Conrad Haynes
 *@author Ethan
 */
public class ImageCanvas extends JPanel {
	// used for serialization
	private static final long serialVersionUID = 1L;
	private Display myPixelViewer;
	private String evaluatedExpression;

	/**
	 * Create canvas within the given Frame and with the given size.
	 */
	public ImageCanvas(Dimension size) {
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		myPixelViewer = new Display(size);
		this.setToolTipText(evaluatedExpression);
		this.repaint();
	}

	public ImageCanvas(String file) {
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		myPixelViewer = new Display(file);
		this.setToolTipText(evaluatedExpression);
		this.repaint();
	}

	/**
	 * Draws the picture.
	 */
	@Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		myPixelViewer.paint(pen);
	}

	/**
	 * Changes the size of the Canvas.
	 */
	@Override
	public void setSize(Dimension size) {
		setPreferredSize(size);
		setMinimumSize(size);
	}
	/**
	 * Changes the underlying image to be drawn.
	 * 
	 * @param p
	 */
	public void setImage(Display p) {
		myPixelViewer = p;
	}
	
	/**
	 * Sets the Expression String.
	 */
	public void setExpression(String str) {
		evaluatedExpression = str;
	}
	
	public void clear() {
		myPixelViewer = new Display(getSize());
	}

}