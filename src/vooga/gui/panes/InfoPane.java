package vooga.gui.panes;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;


import vooga.core.event.IEventHandler;
import vooga.gui.PaneManager;
import vooga.gui.util.ScrollingSpriteDisplay;

/**
 * Type of popover that displays a single piece of information at a time by making use of the
 * ScrollingSpriteDisplay class, at the center of the screen. At the top of the screen you will
 * see the header, a handy label, and "back"becomes available when the user reaches the final
 * step of the instructions.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class InfoPane extends PopoverPane {
	Sprite myHeader;
	String myName;
	ScrollingSpriteDisplay<Sprite> myDisplay;
	int lastIndex;
	boolean reset;
	
	/**
	 * Create a pane that displays info such as instructions 1 at a time. These instructions
	 * can be scrolled through and by default when a user reaches the end of your slides,
	 * they will be able to click "Main Menu" or "Back" to escape.
	 * @param parent the current VoogaGame
	 * @param infoSlides the instructions you want to display
	 * @param header a header image you want to display above the information.
	 */
	public InfoPane(PaneManager parent, BufferedImage[] infoSlides, BufferedImage header) {
		super(parent);

		//Make a sprite from our header
		myHeader=new Sprite(header, 5, 5);
		
		//Create the scrolling display which shows 1 image at a time
		myDisplay=new ScrollingSpriteDisplay<Sprite>(40, header.getHeight()+10, parent.getWidth()-85, 
				parent.getHeight()-header.getHeight(), 1, parent.getParent());
		
		if(infoSlides!=null&&infoSlides.length>0){
			hideButton(MENU_BUTTON, true);
			hideButton(BACK_BUTTON, true);
			for(int i=0;i<infoSlides.length;i++){
				myDisplay.addSprite(new Sprite(infoSlides[i]));
			}
		}
		lastIndex=infoSlides.length-1;
		
		myName="Default";
		myParent.getEventManager().registerEventHandler("ResetInfoPane."+myName, new IEventHandler(){
			@Override
			public void handleEvent(Object o) {
				reset();
			}
			
		});
	}
	
	/**
	 * NEEDS TO BE IN THE UPDATE METHOD OF THIS OBJECT'S PARENT. [ ie: 
	 * if (click()) sendClick(); ] This is how the panel responds to user input.
	 */
	public void sendClick(double mouseX, double mouseY){
		super.sendClick(mouseX, mouseY);
		myDisplay.sendClick(mouseX, mouseY);
		
	}
	
	/**
	 * Sets the slideshow back to the beginning. It's best to do it right after the "show" boolean
	 * has been set to false.
	 */
	public void reset(){
		myDisplay.setStart(0);
	}
	
	/**
	 * Renders this pane. Call this at the end of the parent's draw method to paint over what
	 * you had before.
	 */
	@Override
	public void render(Graphics2D g){
		super.render(g);
		myHeader.render(g);
		myDisplay.render(g);
		
		Boolean b = !(myDisplay.getDisplayStart()==lastIndex);
//		hideButton(MENU_BUTTON, b);
		hideButton(BACK_BUTTON, b);
	}
	
	/**
	 * Set the name of this infoPane, important if you want to reset this pane- fire the event
	 * "ResetInfoPane.[myName]" to start it from 0.
	 * @param newName the string you want to change this pane's name to.
	 */
	public void setName(String newName){
		if(newName!=null){
			myParent.getEventManager().removeEventHandler("ResetInfoPane."+myName);
			myName=newName;
			myParent.getEventManager().registerEventHandler("ResetInfoPane."+myName, new IEventHandler(){
				@Override
				public void handleEvent(Object o) {
					reset();
				}
			});
		}else{
			System.out.println("New name is null... keeping current one instead.");
		}
	}

}
