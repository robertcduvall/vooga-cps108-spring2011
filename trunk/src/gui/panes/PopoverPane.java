package gui.panes;

import gui.VoogaGame;
import gui.util.VoogaButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * Completely customizable Pane that can draw itself. Add buttons and other images, set
 * whether or not you want a "Return to Menu" and "Back" button to be displayed, and more.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class PopoverPane {
	public static int MENU_BUTTON=1;
	public static int BACK_BUTTON=2;
	protected VoogaGame myParent;
	private ArrayList<VoogaButton> myButtons;
	private boolean menuButtonDisabled, backButtonDisabled;
	private SpriteGroup IMAGE_GROUP;
	private Color myBGcolor;
	private Sprite myBGimage;
	protected VoogaButton menuButton;
	protected VoogaButton backButton;
	
	/**
	 * Creates a new customizable popoverPane.
	 * @param parent this object's parent
	 */
	public PopoverPane(VoogaGame parent){
		myParent=parent;
		myButtons = new ArrayList<VoogaButton>();
		
		//TODO: Make some constant buttons for these two-
		BufferedImage img = parent.getImage("/gui/resources/menu_btn_small.gif");
		Dimension position = new Dimension(parent.getWidth()-img.getWidth()-3, parent.getHeight()-img.getHeight());
		menuButton=new VoogaButton(img, "Menu", position);
		

		img = parent.getImage("/gui/resources/back_btn_small.gif");
		position = new Dimension(3, parent.getHeight()-img.getHeight());
		backButton=new VoogaButton(img, "Back", position);
		
		
		IMAGE_GROUP=new SpriteGroup("Info and Supplements");
		IMAGE_GROUP.setBackground(parent.myBackground);
		myBGcolor=Color.LIGHT_GRAY;
		
		menuButtonDisabled=false;
		backButtonDisabled=false;
	}
	
	/**
	 * Calculates which if any buttons are hit by a click at a certain position on the screen.
	 * This way, if you want to do some funky stuff like have clicks filter upside down or something,
	 * that is completely up to you.
	 * @param mouseX
	 * @param mouseY
	 * @return the button that was hit, or null if none was hit.
	 */
	public VoogaButton sendClick(double mouseX, double mouseY){
		//Checks if our 'default' exit buttons are hit
		if(!menuButtonDisabled){
			if(menuButton.hitBy(mouseX, mouseY)) myParent.mainVoogaMenu();
		}
		if(!backButtonDisabled){
			if(backButton.hitBy(mouseX, mouseY)) myParent.closePanes();
		}
		
		//Checks if any user-added buttons are hit
		for (int i=0;i<myButtons.size();i++){
			if(myButtons.get(i).hitBy(mouseX, mouseY)){
				return myButtons.get(i);
			}
		}
		
		return null;
	}
	
	/**
	 * renders background (color and image), supplementary images, buttons, and menu/back.
	 * @param g
	 */
	public void render(Graphics2D g){
		//Render the background
        g.setColor(myBGcolor);
        g.fillRect(0, 0, myParent.getWidth(), myParent.getHeight());
        if(myBGimage!=null){
        	myBGimage.setX(myParent.getWidth()/2-myBGimage.getImage().getWidth()/2); 
        	myBGimage.setY(myParent.getHeight()/2-myBGimage.getImage().getHeight()/2);
        	myBGimage.render(g);
        }
		

		//Render supplementary images
		IMAGE_GROUP.render(g);
		
		//Render buttons
		for (int i=0;i<myButtons.size();i++){
				myButtons.get(i).render(g);
		}
		
		//Render Menu and Back buttons
		if(!menuButtonDisabled){
			menuButton.render(g);
		}
		if(!backButtonDisabled){
			backButton.render(g);
		}
	}
	
	/**
	 * Sets your Background color
	 * @param c color you want to set it to
	 */
	public void setBGColor(Color c){
		myBGcolor=c;
	}
	
	/**
	 * Sets your Background image
	 * @param i image you want to set it to
	 */
	public void setBGImage (BufferedImage i){
		myBGimage=new Sprite(i);
	}
	
	/**
	 * Adds a button to our list of buttons, will be rendered and checked for clicks when: 
	 * sendClick(x,y) is called.
	 * @param v VoogaButton to add.
	 */
	public void addButton(VoogaButton v){
		myButtons.add(v);
	}
	
	/**
	 * Adds this piece of info to our info Group
	 * @param s the sprite you want to add.
	 */
	public void addInfo(Sprite s){
		IMAGE_GROUP.add(s);
	}
	
	/**
	 * Changes a button image- use MENU_BUTTON, BACK_BUTTON, etc- constants.
	 * CAUTION: This is not for your buttons, just Menu and Back.
	 * @param code the static code for your intended target
	 * @param image the bufferedimage you want to make
	 */
	public void changeButtonImage(int code, BufferedImage image){
		VoogaButton b = new VoogaButton();
		if(code==MENU_BUTTON){
			b=menuButton;
		}
		if(code==BACK_BUTTON){
			b=backButton;
		}
		b.setImage(image);
	}
	
	/**
	 * Hides one of the automatic buttons- use MENU_BUTTON, etc- constants.
	 * @param code the static value of your intended target
	 * @param bool true if you want to hide, false if you want to show.
	 */
	public void hideButton(int code, boolean bool){
		if(code==MENU_BUTTON){
			menuButtonDisabled=bool;
		}
		if(code==BACK_BUTTON){
			backButtonDisabled=bool;
		}
	}
}
