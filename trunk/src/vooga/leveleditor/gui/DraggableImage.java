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
	private boolean myFlag = false;
	
	public DraggableImage(ImageIcon icon, JLayeredPane parent){
		super(icon);
		this.icon = icon;
		this.parent = parent;
        parent.add(this,1);
		this.setVisible(true);
		this.setOpaque(true);
		this.setBounds(500, 500, icon.getIconWidth(), icon.getIconWidth());
	}
	protected boolean moveIfSelected(int x, int y){
		if(myFlag){
			if(checkIfSelected(x, y)){
				myFlag = false;
			}	
		}
		else if(!myFlag){
			if(checkIfSelected(x, y)){
				myFlag = true;
			}
		}
		return myFlag;
	}
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean checkIfSelected(int x, int y) {
		return ((this.getX()-this.getWidth())<x && (this.getX()+this.getWidth())>x)
				&& ((this.getY()-this.getHeight())<y) &&(this.getY()+this.getHeight())>y;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {		
		/*we currently do not need this method, may be used if drag and drop is
		 * issues
		 */
		return;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (myFlag){
			x = arg0.getX();
			y = arg0.getY();
			this.setVisible(true);
			this.setOpaque(true);
			this.setBounds(x, y, icon.getIconWidth(), icon.getIconWidth());
			parent.add(this,1);
			parent.moveToFront(this);
		}
		
	}
	
}
