package vooga.gui.examplegame;

import java.awt.Graphics2D;

import vooga.core.VoogaGame;
import vooga.core.VoogaState;


public class ExampleGame extends VoogaGame{
	VoogaState myCurrentState;
	ExamplePaneManager myPaneManager;

	@Override
	public void updatePlayField(long elapsedTime) {
		// Nothing...
	}

	@Override
	public void initResources() {
		myPaneManager=new ExamplePaneManager(this);
		myCurrentState=myPaneManager;
	}
	
	@Override
	public void render(Graphics2D g){
		myCurrentState.render(g);
	}
	
	@Override
	public void update(long elapsedTime){
		if(click()){
			myCurrentState.getEventManager().fireEvent(this, "Click");
		}
		getEventManager().update(elapsedTime);
		myCurrentState.update(elapsedTime);
	}

}
