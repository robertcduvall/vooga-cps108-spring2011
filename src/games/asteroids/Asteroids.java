package games.asteroids;

import java.awt.Graphics2D;

import com.golden.gamedev.object.PlayField;

import vooga.core.*;
import vooga.core.event.IEventHandler;

public class Asteroids extends VoogaGame {
	private PlayField myPlayfield;
	
	@Override
	public void initResources() {
		//TODO: Setup team fill this in.
		setup();
		//fire Event to start menu
		super.fireEvent(this, "start.menu");
		//fire event to start level
		super.fireEvent(this, "start.level");
		super.registerEventHandler("level.complete", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				onLevelComplete();
			}
		});
	}

	private void onLevelComplete() {
		super.fireEvent(this, "level.change");
	}
	
	private void setup() {
		// TODO setup team will fill this in
		
	}

	@Override
	public void render(Graphics2D g) {
		myPlayfield.render(g);
	}

	@Override
	public void updatePlayField(long elapsedTime) {
		myPlayfield.update(elapsedTime);
	}

}
