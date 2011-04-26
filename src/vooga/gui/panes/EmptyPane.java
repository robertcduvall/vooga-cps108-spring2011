/**
 * 
 */
package vooga.gui.panes;

import java.awt.Graphics2D;

import vooga.gui.PaneManager;

/**
 * @author dave
 *
 */
public class EmptyPane extends AbstractPane {

	public EmptyPane(PaneManager parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see vooga.gui.panes.AbstractPane#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		return;

	}

	/* (non-Javadoc)
	 * @see vooga.gui.panes.AbstractPane#sendClick(double, double)
	 */
	@Override
	public void sendClick(double mouseX, double mouseY) {
		return;

	}

	/* (non-Javadoc)
	 * @see vooga.gui.panes.AbstractPane#update(double)
	 */
	@Override
	public void update(double elapsedTime) {
		return;

	}

}
