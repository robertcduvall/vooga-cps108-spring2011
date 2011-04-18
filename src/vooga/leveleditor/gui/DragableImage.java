package vooga.leveleditor.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DragableImage extends JLabel {
	public DragableImage(ImageIcon icon){
		super(icon);
		this.setVisible(true);
		this.setOpaque(true);
		this.setBounds(20, 20, icon.getIconWidth(), icon.getIconWidth());
	}
}
