package vooga.arcade.gui.drawTools;

import java.awt.Graphics;
import java.awt.Graphics2D;

//Display hierarchy used for different paint methods
public interface IDrawable {

	public void painter(Graphics2D g, Display p);

}
