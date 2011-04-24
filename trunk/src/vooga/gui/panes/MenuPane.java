package vooga.gui.panes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.object.Sprite;

import vooga.gui.PaneManager;
import vooga.gui.util.VoogaButton;

/**
 * Popover pane that displays many menu options (buttons) which are easily configurable using an integer
 * input system. If we have 5 buttons, each of these can return an integer- that corresponds to an action
 * in parent. So input is still handled and returned by this class rather than VoogaGame.
 * 
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class MenuPane extends PopoverPane{
	Sprite myHeader;
	List<VoogaButton> myMenuOptions=new ArrayList<VoogaButton>();

	public MenuPane(List<VoogaButton> buttonList, PaneManager parent){
		super(parent);
		myMenuOptions=buttonList; //so that I can manage how they're arranged
		myButtons.addAll(myMenuOptions); //so that super.sendClick handles them
	}
	
	public void addMenuOption(VoogaButton v){
		if (v!=null){
			myMenuOptions.add(v);
			myButtons.add(v);
		}
	}
	
	public void addHeader(BufferedImage headerImage){
		myHeader=new Sprite(headerImage);
		addInfo(myHeader);
	}	
	
	public void addHeader(Sprite headerSprite){
		myHeader=headerSprite;
		addInfo(myHeader);
	}



	/**Places our components in the "default menu" setup*/
	public void placeComponents(){

		double myCenterX=myParent.getWidth()/2;
		double myCenterY=myParent.getHeight()/2;

		//Calculate Height of all buttons
		int buttonsHeight=0;
		for (VoogaButton button: myMenuOptions){
			buttonsHeight+=button.getImage().getHeight();
		}
//		buttonsHeight+=myMenuOptions.size()*myMenuOptions;

		if(myHeader!=null){
			//Correctly set position of header
			double headerX=myCenterX-myHeader.getImage().getWidth()/2;
			double headerY=myCenterY-myHeader.getImage().getHeight()-buttonsHeight/2;          
			myHeader.setLocation(headerX, headerY);
			
		}

		//Correctly set positions of buttons
		double spaceSize=5;
		double currentY=myCenterY-buttonsHeight/2+spaceSize;
		for (VoogaButton button: myMenuOptions){
			button.setPosition(new Dimension(
					(int) (myCenterX-button.getImage().getWidth()/2), (int) currentY));
			currentY+=button.getImage().getHeight()+spaceSize;
		}
	}
	

	/**
	 * renders background (color and image), supplementary images, buttons, and menu/back.
	 * @param g
	 */
	public void render(Graphics2D g){
		placeComponents();
		super.render(g);
	}
	
}
