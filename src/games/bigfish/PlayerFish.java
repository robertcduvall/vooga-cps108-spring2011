package games.bigfish;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerFish extends FishSprite {

	private final double SWIM_SPEED = 2;
	private VoogaGame myGame;
	private int mySize;
	
	public PlayerFish(VoogaGame game, int x, int y, int size) {

    	super(game.getImageLoader().getImage("fish"+size,Direction.WEST),x,y);
		mySize = size;
		myGame = game;

		game.registerEventHandler("Input.User.Down", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				//accelerate(SWIM_SPEED, 90);

				VerticalSwim(SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Up", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				//accelerate(SWIM_SPEED, 270);

				VerticalSwim(-1*SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Left", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				//accelerate(SWIM_SPEED, 180);

				HorizontalSwim(-1*SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Right", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				//accelerate(SWIM_SPEED, 0);
				HorizontalSwim(SWIM_SPEED);
			}

			
		});

		
	}

	private void HorizontalSwim(double d) {
		this.moveX(d);
		//accelerate(.0008);
	}
	
	private void VerticalSwim(double d) {
		this.moveY(d);
		//accelerate(.0008);
	}

}
