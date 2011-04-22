package vooga.gui.examplegame;

import vooga.gui.VoogaGame;  
import vooga.gui.interfaces.IPaneManager;
import vooga.gui.panes.InfoPane;
import vooga.gui.panes.MenuPane;
import vooga.gui.panes.PopoverPane;
import vooga.gui.util.NumberDisplay;
import vooga.gui.util.ScrollingSpriteDisplay;
import vooga.gui.util.VoogaButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.object.Sprite;



public class ExampleGame extends VoogaGame implements IPaneManager {
	ArrayList<PopoverPane> myPanes;
	PopoverPane activePane, mainPane;
	InfoPane myInfo;
	boolean displayActivePane, BSOD;
	long myScore;
	ExampleUI myUI;

	public ExampleGame(GameEngine arg0) {
		super(arg0);
	}

	@Override
	public void initResources() {
		BSOD=true;
		
		//Just an example- store however you like.
		myPanes=new ArrayList<PopoverPane>();
		
		//Create a menu from a premade list of VoogaButtons
		BufferedImage image= getImage("gui/resources/generic/button.gif");
		ArrayList<VoogaButton> premadeList = new ArrayList<VoogaButton>();
		VoogaButton b=new VoogaButton(image, "cat", new Dimension(400,400));
		premadeList.add(b);
		image= getImage("gui/resources/generic/button.gif");
		b=new VoogaButton(image, "dog", new Dimension(400,400));
		premadeList.add(b);
		image= getImage("gui/resources/generic/button.gif");
		b=new VoogaButton(image, "armadillo", new Dimension(400,400));
		premadeList.add(b);
		
		//Create MenuPane from premadeList with a seed of 10
		MenuPane m = new MenuPane(premadeList, 10, this);
		m.addHeader(getImage("/gui/resources/generic/title.gif"));
		mainPane=m;

		//Assemble your menu on the fly
	    BufferedImage image2 = getImage("/gui/resources/generic/header.gif");
		MenuPane l = new MenuPane(new ArrayList<VoogaButton>(), 5, this);
		l.addHeader(getImage("/gui/resources/generic/title.gif"));
		VoogaButton b2=new VoogaButton(image2, "nancy", new Dimension(200,200));
		l.addMenuOption(b2);
		b2=new VoogaButton(image2, "scott", new Dimension(200,200));
		l.addMenuOption(b2);
		b2=new VoogaButton(image2, "ford", new Dimension(200,200));
		l.addMenuOption(b2);
		myPanes.add(l);
		
		
		
		//Make an instructions panel
		InfoPane i;
		BufferedImage[] instructions = new BufferedImage[10];
		int slide=0;
		while (slide<10){
			image = getImage("gui/resources/number"+slide+".gif");
			instructions[slide]=image;
			slide++;
		}
		image= getImage("gui/resources/generic/header.gif");
		i=new InfoPane(this, instructions, image);
		myPanes.add(i);
		myInfo=i;
		
		activePane=myPanes.get(0);
		
		myUI=new ExampleUI(this);

	}

	@Override
	public void render(Graphics2D g) {
		//just render a blue box
	    g.setColor(Color.CYAN);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    
		if(!BSOD){
		    mainPane.render(g);
		}else{
			myUI.render(g);	
		}
	    
	    if(displayActivePane==true){
		    activePane.render(g);
	    }

	}

	@Override
	public void update(long elapsed) {
		if(click()){
			myScore+=500;
			
				if(displayActivePane==true) 
					activePane.sendClick(getMouseX(), getMouseY());
				
				else{
					
					if(BSOD){
					myUI.sendClick(getMouseX(), getMouseY());
				}else{
					 mainPane.sendClick(getMouseX(), getMouseY());
				}
			}
		}
		

		
		if(bsInput.isKeyPressed(KeyEvent.VK_B)){
			BSOD=!BSOD;
		}
		
		if(BSOD){
			if (keyPressed(KeyEvent.VK_ENTER)) {
	        	myScore=Long.MAX_VALUE;
	        }
		}

	}

	@Override
	public void closePanes() {
		System.out.println("You clicked a back button!");
		if(displayActivePane){
		displayActivePane=false;
		}else{
			BSOD=true;
		}
		
	}

	@Override
	public void mainMenu() {
		System.out.println("You clicked a menu button.");
		
	}

	/**
	 * Generates/executes the correct action based on an integer.
	 */
	@Override
	public void sendAction(int id) {
		//For debug/display purposes:
		System.out.println("Recieved action! "+id);
		
		if(id==102){ //Pane with seed 10 (mainPane) has recieved a buttonClick with id 2.
			//fire an event
			//anything else
			activePane=myPanes.get(0);
			displayActivePane=true;
		}if(id==101){ 
			activePane=myPanes.get(1);
			myInfo.reset();
			displayActivePane=true;
		}
		if(id==51){ //Pane with seed 5 has recieved a buttonClick with id 1;
			displayActivePane=false;
		}
		
	}
	
	public long getScore(){
		return myScore;
	}

}
