/**
 * 
 */
package vooga.arcade.view.gamePanel;

import javax.swing.ImageIcon;

import vooga.arcade.parser.ArcadeUserObject;

/**
 * @author Ethan Goh
 */
public class ArcadeUserPanel extends ArcadePanel
{
	private static final long serialVersionUID = 1L;

	public ArcadeUserPanel(ArcadeUserObject gameObject)
	{
		super(new ImageIcon(gameObject.getImage()), gameObject.getName());
	}
}
