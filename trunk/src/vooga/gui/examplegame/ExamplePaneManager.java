package vooga.gui.examplegame;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.gui.PaneManager;
import vooga.gui.panes.InfoPane;
import vooga.gui.panes.MenuPane;
import vooga.gui.util.VoogaButton;

public class ExamplePaneManager extends PaneManager {

	public ExamplePaneManager(VoogaGame parent) {
		super(parent);
	}
	
	@Override
	protected void initPanes() {

		//Create a menu ("MainMenu") from a premade list of VoogaButtons
		BufferedImage image= getImage("resources/aGenericButton.gif");
		ArrayList<VoogaButton> premadeList = new ArrayList<VoogaButton>();
		VoogaButton b=new VoogaButton(image, "SubMenu", new Dimension(400,400));
		premadeList.add(b);
		image= getImage("resources/aGenericButton.gif");
		b=new VoogaButton(image, "Instructions", new Dimension(400,400));
		premadeList.add(b);
		image= getImage("resources/aGenericButton.gif");
		b=new VoogaButton(image, "DoNothing", new Dimension(400,400));
		premadeList.add(b);
		//Create MenuPane from premadeList with a seed of 10
		MenuPane m = new MenuPane(premadeList, this);
		m.addHeader(getImage("resources/aGenericTitle.gif"));
		myPanes.add(m);

		//Assemble your menu ("SubMenu") on the fly
		MenuPane l = new MenuPane(new ArrayList<VoogaButton>(), this);
		l.addHeader(getImage("resources/aGenericHeader.gif"));
		BufferedImage image2 = getImage("resources/menuButton.gif");
		VoogaButton b2=new VoogaButton(image2, "MainMenu", new Dimension(200,200));
		l.addMenuOption(b2);
		b2=new VoogaButton(image2, "DoNothing2", new Dimension(200,200));
		l.addMenuOption(b2);
		b2=new VoogaButton(image2, "DoNothing3", new Dimension(200,200));
		l.addMenuOption(b2);
		myPanes.add(l);
		
		
		//Make an instructions panel ("Instructions")
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

	}

	@Override
	protected void createEventHandlers() {
		getEventManager().registerEventHandler("MainMenu", new IEventHandler()
		{
			public void handleEvent(Object o)
			{
				openDefaultPane();
			}
		});
		
		getEventManager().registerEventHandler("SubMenu", new IEventHandler()
		{
			public void handleEvent(Object o)
			{
				openPane(1);
			}
		});
		
		getEventManager().registerEventHandler("Instructions", new IEventHandler()
		{
			public void handleEvent(Object o)
			{
				myEventManager.fireEvent(this, "ResetInfoPane.Default");
				openPane(2);
			}
		});
		
		getEventManager().registerEventHandler("Back", new IEventHandler()
		{
			public void handleEvent(Object o)
			{
				backButton();
			}
		});
		
		getEventManager().registerEventHandler("Click", new IEventHandler()
		{
			public void handleEvent(Object o)
			{
				sendClickToActivePane();
			}
		});

	}

	protected void backButton() {
		if(getCurrentPane()!=0){
			openDefaultPane();
		}else{
			fireParentEvent("PlayLevel");
		}
	}

}
