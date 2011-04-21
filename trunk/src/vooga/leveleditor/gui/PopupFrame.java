package vooga.leveleditor.gui;

import javax.swing.JInternalFrame;

public class PopupFrame extends JInternalFrame{
	public PopupFrame(int x, int y){
		super("Example", true, true, true, true);
		this.setSize(200, 200);
		this.setLocation(x, y);
		this.show();
	}
}
