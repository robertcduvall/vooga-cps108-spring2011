package vooga.leveleditor.gui;

import java.awt.TextComponent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import org.w3c.dom.Element;
/**
 * 
 * @author Charlie Hatcher
 *
 */
public class DraggableImage extends JLabel implements MouseMotionListener{
	private int x,y;
	private ImageIcon myIcon;
	private Viewport myParent;
	private Element myProperties;
	private boolean myFlag = false;
	
	public DraggableImage(Viewport parent, Element data){
		super();
		this.myProperties=data;
		String name = myProperties.getElementsByTagName("name").item(0).getTextContent();
        String xs = myProperties.getElementsByTagName("x").item(0).getTextContent();
        String ys = myProperties.getElementsByTagName("y").item(0).getTextContent();
        String imgs = myProperties.getElementsByTagName("image").item(0).getTextContent();
        x = new Integer(xs);
        y = new Integer(ys);
        this.myIcon = new ImageIcon(imgs);
        this.myParent = parent;
        this.setIcon(myIcon);
		setJLabelValuesForDraggableImage(myIcon, parent, x, y);
	}
	
	protected void setSpriteProperties(int x,int y){
		if(checkIfSelected(x, y)){
			PopupFrame frame = new PopupFrame(x,y);
			myParent.add(frame);
		}
	}
	
	/**
	 * Moves the DraggableImage if the image is selected, if the DraggableImage is
	 * currently selected, and the user clicks, the image will drop. If the user clicks
	 * on the screen and the mouse's position is not in the image, the image does not
	 * move.
	 * @param x
	 * @param y
	 * @return
	 */
	protected boolean moveIfSelected(int x, int y){
		if(myFlag){
			setFlagOnClick(x, y, false);
		}
		else if(!myFlag){
			setFlagOnClick(x,y, true);
			
			
		}
		return myFlag;
	}

	/**
	 * If the DraggableImage is selected, set the DraggableImage's x and y coordinates
	 * to the x and y value of the mouse.
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (myFlag){
			x = arg0.getX();
			y = arg0.getY();
			setJLabelValuesForDraggableImage(myIcon, myParent, x, y);
		}
	}
	
	/**
	 * 
	 * Sets all the necessary parameters to the DraggableImage in order to see it on
	 * the viewport. Moves the new Image to the front of layeredpane.
	 * @param icon
	 * @param parent
	 */
	private void setJLabelValuesForDraggableImage(ImageIcon icon,
			Viewport parent, int x, int y) {
        parent.add(this, 1);
		this.setVisible(true);
		this.setBounds(x, y, icon.getIconWidth(), icon.getIconWidth());
		parent.moveToFront(this);
	}
	
	/**
	 * Checks to see if the DraggableImage is selected, sets the flag on the click.
	 * @param x
	 * @param y
	 * @param boolean resulting_flag
	 */
	private void setFlagOnClick(int x, int y, boolean resulting_flag) {
		if(checkIfSelected(x, y)){
			myFlag = resulting_flag;
		}
	}
	
	/**
	 * Checks to see if the mouse's position is inside the DraggableImage.
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
		
	}
}
