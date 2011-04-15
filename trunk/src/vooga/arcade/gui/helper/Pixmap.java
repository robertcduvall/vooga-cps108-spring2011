package vooga.arcade.gui.helper;
import java.awt.Graphics2D;

import vooga.arcade.gui.drawTools.Display;
import vooga.arcade.gui.drawTools.IDrawable;

/**
 * Represents an image that supports changing individual pixel colors.
 * 
 * Only the following formats are supported: png, jpg, gif.
 * 
 * @author Robert C. Duvall
 */

public class Pixmap extends Display implements IDrawable
{

	public static final String DEFAULT_NAME = "";

	public Pixmap()
	{
		super();
	}

	/**
	 * Draws this pixmap on the screen.
	 */
	@Override
	public void painter(Graphics2D g, Display p) {
		g.drawImage(p.getBackgroundImage(), 0, 0, p.getSize().width,
				p.getSize().height, null);
	}

}