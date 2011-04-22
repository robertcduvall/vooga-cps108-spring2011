package vooga.leveleditor.gui;

import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PopupFrame extends JInternalFrame{
	public PopupFrame(int x, int y){
		super("Example", true, true, true, true);
		this.setSize(200, 200);
		this.setLocation(x, y);
		JScrollPane scroll = new JScrollPane(new SpriteProperties());
		this.add(scroll);
		this.show();
	}
}
