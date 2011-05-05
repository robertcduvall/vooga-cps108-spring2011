package vooga.gui.util;

import java.awt.Color; 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import vooga.core.VoogaGame;
import vooga.user.main.ResourceManager;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;

/**
 * A display of sprites that can scroll from left to right when arrows are clicked.
 * 
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class ScrollingSpriteDisplay<T extends Sprite> {
	//TODO: General- right now 'spaces' are hardcoded at 5px (used when myLeftArrow/myRightArrow are created
	//and in setting X and Y for sprites in setDisplayed. Should probably use a variable instead.
	
	//State
	private SpriteGroup myDisplayedSprites;
	private ArrayList<T> mySprites;
	private Sprite myLeftArrow, myRightArrow;
	private T myActiveSprite;
	private int myHeight, myWidth, myStart, myNumberSpritesDisplayed;
	private double myX, myY;
	private VoogaGame myParent;
	private ResourceManager defaultResources = new ResourceManager("resources.gui.Defaults");
	
	
	//Constructors
	/**
	 * Creates a scrollingSpriteDisplay with your specified position and size, with a number of
	 * sprites to display at one time.
	 * 
	 * The rendered object will actually need sightly more space based on the left and right arrows you are
	 * using. These are not accounted for in width and height, so width+right_arrow's width+space(5) is the
	 * furthest right it will be. And width-left arrow's width is the furthest left it will be.
	 * 
	 * @param x the x coordinate to start from
	 * @param y the y coordinate to start from
	 * @param width the width of our scrolling display
	 * @param height the height of our scrolling display
	 * @param numDisplayedSprites the number of sprites to display at any one time
	 * @param parent this object's parent
	 */
	public ScrollingSpriteDisplay(double x, double y, int width, int height, int numDisplayedSprites, VoogaGame parent){
		//Initialize "easy" values
		initValues(x, y, width, height, numDisplayedSprites);
		
		//Creates a blank arraylist to be filled with the display's sprites.
		mySprites = new ArrayList<T>();
		
		//Parent object- useful to get height, width, mouse interactions, and File/Input/Music options
		myParent=parent;
		
		//Funky Group constructor stuff:
		myDisplayedSprites=new SpriteGroup("Displayed Sprites Group");
		myDisplayedSprites.setBackground(new ColorBackground(Color.LIGHT_GRAY));
		
		//Draw the arrows to the left and right of the display.
		makeArrows();
	}
	
	/**
	 * Constructor helper
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param numDisplayedSprites
	 */
	private void initValues(double x, double y, int width, int height, int numDisplayedSprites){
		myX=x;
		myY=y;
		myHeight=height;
		myWidth=width;
		myNumberSpritesDisplayed=numDisplayedSprites;
		myStart=0;
	}
	
	/**
	 * constructor helper
	 */
	private void makeArrows(){
		String leftArrowImage=defaultResources.getString("LeftArrowFilepath");
		BufferedImage image = myParent.getImage(leftArrowImage);
		myLeftArrow = new Sprite(image, myX-image.getWidth()-5,myY+(myHeight/2-image.getHeight()/2));

		String rightArrowImage=defaultResources.getString("RightArrowFilepath");
		image = myParent.getImage(rightArrowImage);
		myRightArrow = new Sprite(image, myX+myWidth+5,myY+(myHeight/2-image.getHeight()/2));
	}
	
	
	//Golden T Stuff:
	/**
	 * Correctly renders our displayed items as well as left and right arrows!
	 */
	public void render(Graphics2D g){
		myLeftArrow.render(g);
		myRightArrow.render(g);
		myDisplayedSprites.render(g);
	}
	
	/**
	 * Checks if any of the displayed elements of our ScrollingSpriteDisplay have been clicked.
	 * If so, and it's the left arrow, go left. If it's the right arrow, go right. If it's a sprite,
	 * make that our active sprite and return it. Feel free to ignore the return if it's not relevant.
	 * 
	 * @param mouseX the X position of the mouse, used to see if you clicked something relevant
	 * @param mouseY the Y position of the mouse, used to see if you clicked something relevant
	 * @return the active sprite of this ScrollingSpriteDisplay. Do with it what you will.
	 */
	@SuppressWarnings("unchecked")
	public T sendClick(double mouseX, double mouseY){
		//If we hit the left arrow, goLeft.
		if(mouseX>myLeftArrow.getX()&&mouseX<myLeftArrow.getX()+myLeftArrow.getWidth()){
			if(mouseY>myLeftArrow.getY()&&mouseY<myLeftArrow.getY()+myLeftArrow.getHeight()){
				goLeft();
			}
		}
		//If we hit the right arrow, goRight.
		if(mouseX>myRightArrow.getX()&&mouseX<myRightArrow.getX()+myRightArrow.getWidth()){
			if(mouseY>myRightArrow.getY()&&mouseY<myRightArrow.getY()+myRightArrow.getHeight()){
				goRight();
			}
		}
		
		//TODO: Kind of useless if we use this here, we're no longer checking via the parameters...
		//Idk how else to do it though.
		
		//Check if we hit a Sprite that is being displayed
		if(myDisplayedSprites.getSize()>0){
			T[] displayedSprites=(T[]) myDisplayedSprites.getSprites();
			for(int i=0;i<displayedSprites.length-1;i++){
				if(displayedSprites[i]!=null){
					if(myParent.checkPosMouse(displayedSprites[i], true)){
						myActiveSprite=displayedSprites[i];
					}
				}
			}
		}
		
		return myActiveSprite;
	}
	
	
	//Useful Methods
	/**
	 * Changes our display to reflect the left arrow being clicked.
	 */
	private void goLeft(){
		if (!(myStart-myNumberSpritesDisplayed<0)){
			myStart -= myNumberSpritesDisplayed;
		}
		else{
			myStart=0;
		}
		setDisplayed(myStart);
	}
	
	/**
	 * Changes our display to reflect the right arrow being clicked.
	 */
	private void goRight(){
		int maxStart=mySprites.size()-myNumberSpritesDisplayed;
		if (maxStart<0) maxStart=0;
		
		if (!(myStart+myNumberSpritesDisplayed>maxStart)){
			myStart += myNumberSpritesDisplayed;
		}
		else{
			myStart = maxStart;
		}
		setDisplayed(myStart);
	}
	
	/**
	 * Adds a sprite to our list of sprites.
	 * @param t the sprite you want to add
	 * @return true if it could be added, false if it could not.
	 */
	public boolean addSprite(T t){
		boolean ret = mySprites.add(t);
		setDisplayed(myStart);
		return ret;
	}
	
	/**
	 * Adds all sprites from a sprite group to the list 
	 * 
	 * ... ***CAUTION*** ... No idea if this works or not
	 * 
	 * @param sg the sprite group you want to add sprites from.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void addSprites(SpriteGroup sg){
		T[] spriteArray=(T[]) sg.getSprites();
		for (int i=0;i<spriteArray.length;i++){
			//TODO: Can't find a way to check for instanceof T- gives me some shit about runtime...
			mySprites.add(spriteArray[i]);
		}
		setDisplayed(myStart);
	}
	
	/**
	 * Removes the sprite t from our array if it exists.
	 * @param t
	 */
	public void removeSprite(T t){
		//TODO: Boolean return true or false? Would be very easy, probably just as easy as typing this todo...
		mySprites.remove(t);
		setDisplayed(myStart);
	}
	
	/**
	 * Updates our display to match the current state of our list. Called when an "event" happens, like
	 * a sprite being added, removed, or the range to be displayed changing.
	 * @param displayStart
	 */
	private void setDisplayed(int displayStart){
		myDisplayedSprites.clear();
		for(int i=displayStart;i<displayStart+myNumberSpritesDisplayed;i++){
			if(i<mySprites.size()){
				T temp = mySprites.get(i);
				temp.setX(myX+((i-displayStart)*(myWidth/myNumberSpritesDisplayed)+5));
				temp.setY(myY+(myHeight/2-temp.getImage().getHeight()/2));
				if(myNumberSpritesDisplayed==1){
					temp.setX(myX+myWidth/2-temp.getImage().getWidth()/2);
//					temp.setY(myY+(myHeight/2-temp.getImage().getHeight()/2));
				}
				myDisplayedSprites.add(temp);
			}
		}
	}
	
	/**
	 * Sets up to start at "start". re-draws the list
	 * @param start
	 */
	public void setStart(int start){
		if(start>=0&&start<mySprites.size()-myNumberSpritesDisplayed) myStart=start;
		setDisplayed(myStart);
		
	}
	
	
	//Getters & Setters	
	/**
	 * Set the position of the ScrollingSpriteDisplay
	 */
	public void setPosition(double x, double y){
		myX=x;
		myY=y;
		//TODO: update position of arrows and myDisplayedSprites to match this.
	}
	
	/**
	 * Set the number of sprites to be displayed at one time.
	 * @param numberSpritesToDisplay
	 */
	public void setNumberSpritesDisplayed(int numberSpritesToDisplay){
		myNumberSpritesDisplayed=numberSpritesToDisplay;
	}
	
	/**
	 * @return the active sprite for this ScrollingSpriteDisplay
	 */
	public T getActiveSprite(){
		return myActiveSprite;
	}

	/**
	 * Tries to set the active sprite for this ScrollingSpriteDisplay
	 * @param t the sprite you want to set as the active sprite
	 * @return true if it works, false if it does not (ie- this SSD does not contain t)
	 */
	public boolean setActiveSprite(T t){
		for (T temp: mySprites){
			if(t.equals(temp)){
				myActiveSprite=t;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return the index we are starting our display from.
	 */
	public int getDisplayStart(){
		return myStart;
	}
	
	
}
