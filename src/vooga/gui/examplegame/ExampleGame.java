package vooga.gui.examplegame;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.gui.panes.InfoPane;
import vooga.gui.panes.MenuPane;
import vooga.gui.panes.PopoverPane;
import vooga.gui.util.VoogaButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;




public class ExampleGame extends VoogaGame{
	ArrayList<PopoverPane> myPanes;
	PopoverPane activePane, mainPane;
	InfoPane myInfo;
	boolean displayActivePane, BSOD;
	long myScore;
	ExampleUI myUI;
	EventManager myEvents=getEventManager();

	public ExampleGame() {
		super("Resources");
	}

	@Override
	public void initResources() {
		createEventHandler();

		//Just an example- store however you like.
		myPanes=new ArrayList<PopoverPane>();

		//Create a menu from a premade list of VoogaButtons
		BufferedImage image= getImage("resources/aGenericButton.gif");
		ArrayList<VoogaButton> premadeList = new ArrayList<VoogaButton>();
		VoogaButton b=new VoogaButton(image, "Submenu", new Dimension(400,400));
		premadeList.add(b);
		image= getImage("resources/aGenericButton.gif");
		b=new VoogaButton(image, "Instructions", new Dimension(400,400));
		premadeList.add(b);
		image= getImage("resources/aGenericButton.gif");
		b=new VoogaButton(image, "DoNothing", new Dimension(400,400));
		premadeList.add(b);

		//Create MenuPane from premadeList with a seed of 10
		MenuPane m = new MenuPane(premadeList, 10, this);
		m.addHeader(getImage("resources/aGenericTitle.gif"));
		mainPane=m;

		//Assemble your menu on the fly
		BufferedImage image2 = getImage("resources/aGenericHeader.gif");
		MenuPane l = new MenuPane(new ArrayList<VoogaButton>(), 5, this);
		l.addHeader(getImage("resources/menuButton.gif"));
		VoogaButton b2=new VoogaButton(image2, "Instructions", new Dimension(200,200));
		l.addMenuOption(b2);
		b2=new VoogaButton(image2, "DoNothing2", new Dimension(200,200));
		l.addMenuOption(b2);
		b2=new VoogaButton(image2, "DoNothing3", new Dimension(200,200));
		l.addMenuOption(b2);
		myPanes.add(l);


		BSOD=true;



		//Make an instructions panel
		InfoPane i;
		BufferedImage[] instructions = new BufferedImage[10];
		int slide=0;
		while (slide<10){
			image = getImage("resources/numbers/"+slide+".gif");
			instructions[slide]=image;
			slide++;
		}
		image= getImage("resources/aGenericHeader.gif");
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
		super.update(elapsed);

		if(click()){
			myScore+=500;

			if(displayActivePane==true) {
				activePane.sendClick(getMouseX(), getMouseY());
			} else{

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

	public void createEventHandler()
	{
		myEvents.registerEventHandler("GUI.Back", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				if(displayActivePane){
					displayActivePane=false;
				}else{
					BSOD=true;
				}
			}
		});
		
		myEvents.registerEventHandler("GUI.Menu", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				BSOD=false;
				displayActivePane=false;
			}
		});

		myEvents.registerEventHandler("GUI.Submenu", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				activePane=myPanes.get(0);
				displayActivePane=true;
			}
		});
		
		myEvents.registerEventHandler("GUI.Instructions", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				activePane=myPanes.get(1);
				myInfo.reset();
				displayActivePane=true;
			}
		});
		
		myEvents.registerEventHandler("GUI.DoNothing3", new IEventHandler()
		{
			@Override
			public void handleEvent(Object o)
			{
				System.out.println("You did nothing... successfully!");
			}
		});
	}


	public long getScore(){
		return myScore;
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		// TODO Auto-generated method stub

	}

}
