package games.bigfish;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerFish extends FishSprite {

	private final double SWIM_SPEED = 2;
	private VoogaGame myGame;
	private int fishEaten;
	
	public PlayerFish(VoogaGame game, int x, int y, int size) {

    	super(game.getImageLoader().getImage("fish"+size,Direction.WEST),x,y);
		mySize = size;
		myGame = game;

		game.registerEventHandler("Input.User.Down", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				VerticalSwim(SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Up", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				VerticalSwim(-1*SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Left", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				HorizontalSwim(-1*SWIM_SPEED);
			}
		});

		game.registerEventHandler("Input.User.Right", new IEventHandler() {

			@Override
			public void handleEvent(Object o) {
				HorizontalSwim(SWIM_SPEED);
			}
		});
	}
	
	public void addToEaten(){
		fishEaten ++;
	}

	private void HorizontalSwim(double d) {
		this.moveX(d);
	}
	
	private void VerticalSwim(double d) {
		this.moveY(d);
	}
	

}
