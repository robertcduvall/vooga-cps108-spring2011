package vooga.user.view.gui;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A container that contains all the information associated with the Image
 * displayed on the screen.
 *@author Conrad Haynes
 *@author Ethan
 */
public class ImageCanvas extends JPanel {
	// used for serialization
	private static final long serialVersionUID = 1L;
	private String evaluatedExpression;

	/**
	 * Create canvas within the given Frame and with the given size.
	 */
	public ImageCanvas(Dimension size) {
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setToolTipText(evaluatedExpression);
		this.repaint();
	}

	public ImageCanvas(String file) {
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setToolTipText(evaluatedExpression);
		this.repaint();
	}

	/**
	 * This method is called automatically to update the underground image of the GUI. currently added for extensibility
	 */
	@Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
	}
	
	/**
	 * Changes the size of the Canvas.
	 */
	@Override
	public void setSize(Dimension size) {
		setPreferredSize(size);
		setMinimumSize(size);
	}

}