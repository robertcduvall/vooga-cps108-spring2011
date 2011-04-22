/**
 * 
 */
package gui.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ColorBackground;
 
/**
 * Displays numbers at a position. Supports commas and no commas.
 * @author Dave Crowe, David Colon-Smith
 *
 */
public class NumberDisplay {
	//State
	SpriteGroup mySprites;
	private long myNumber;
	private String myString;
	private double myX;
	private double myY;
	GameObject myParent;
	private boolean showCommas=false;

	//Constructor
	/**
	 * Displays a number, default with no commas.
	 * @param x the number's x position, it will grow right.
	 * @param y the number's y position
	 */
	public NumberDisplay(double x, double y, GameObject parent){
		myParent=parent;
		myX=x;
		myY=y;
		
		//Funky Group constructor stuff:
		mySprites=new SpriteGroup("Displayed Sprites Group");
		mySprites.setBackground(new ColorBackground(Color.LIGHT_GRAY));
	}
	
	//Methods
	/**
	 * Sets the number of this numberDisplay.
	 * @param num the number you want to display
	 */
	public void setNumber(long num){
		if(num!=myNumber){
			myNumber=num;
			myString=""+myNumber;
			
			if(showCommas==true)
				myString=addCommas(myString);
			
			makeSprites();
		}
	}
	
	/**
	 * Turn off and on commas for your number display. Default is false.
	 * @param bool true if you want to show commas, false if you want to hide them.
	 */
	public void showCommas(boolean bool){
			showCommas=bool;
			
			//Re-sprite the string so it has commas. Have to do it here as
			//in setNumber, if the number is the same we don't bother re-loading images for sprites.
			myString=""+myNumber;
			if(showCommas==true)
				myString=addCommas(myString);
			makeSprites();
	}
	
	/**
	 * Adds commas to the string every 3 places from the right
	 * @param s the string you want to add commas to
	 * @return a string with commas added
	 */
	private String addCommas(String s){
		String ret="";
		for (int i=0;i<s.length();i++){
			if(i%3==0&&i!=0){
				ret=s.substring(s.length()-1-i,s.length()-i)+","+ret;
			}else{
				ret=s.substring(s.length()-1-i,s.length()-i)+ret;
			}
		}
		return ret;
	}
	
	/**
	 * Clears sprite group, adds sprites to represent the "myNumber" as "myString"
	 */
	private void makeSprites(){
		mySprites.clear();
		if(myNumber==0){
			myString=0+myString;
		}
		BufferedImage image;
		for (int i=0;i<myString.length();i++){
			if(myString.charAt(i)==','){
				image = myParent.getImage("/gui/resources/comma.gif");
				mySprites.add(new Sprite(image, myX+i*image.getWidth(), myY));
			}else{
				image = myParent.getImage("/gui/resources/number"+myString.charAt(i)+".gif");
				mySprites.add(new Sprite(image, myX+i*image.getWidth(), myY));
			}
		}
	}
	
	/**
	 * renders the number
	 * @param g
	 */
	public void render(Graphics2D g){
		mySprites.render(g);
	}
	
}
