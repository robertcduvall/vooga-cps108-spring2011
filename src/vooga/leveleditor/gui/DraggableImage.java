package vooga.leveleditor.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class DraggableImage extends JLabel implements MouseMotionListener{
	private int x,y;
	private ImageIcon icon;
	private JLayeredPane parent;
	
	public DraggableImage(ImageIcon icon, JLayeredPane parent){
		super(icon);
		this.icon = icon;
		this.parent = parent;
        parent.add(this);
		this.setVisible(true);
		this.setOpaque(true);
		this.setBounds(500, 500, icon.getIconWidth(), icon.getIconWidth());
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {		
	
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		parent.remove(this);
		this.setVisible(true);
		this.setOpaque(true);
		this.setBounds(x, y, icon.getIconWidth(), icon.getIconWidth());
		parent.add(this);
	}
}
