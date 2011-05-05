package games.bigfish;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerFish extends FishSprite {

	private final double SWIM_SPEED = 2;
	//private VoogaGame myGame;
	private int fishEaten;
	private VoogaGame myGame;
	
	public PlayerFish(VoogaGame game, int x, int y, int size) {

    	super(game.getImageLoader().getImage("fish"+size,Direction.WEST),x,y,size);
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
		checkLevelUp();
	}

	private void checkLevelUp() {
		if(fishEaten > Math.pow(mySize, 3)){
			mySize++;
			this.setImage(myGame.getImageLoader().getImage("fish"+mySize,Direction.WEST));
			if(getX() + getWidth() > myGame.getWidth()){
				setX(myGame.getWidth() - getWidth());
			}
			if(getY() + getHeight() > myGame.getHeight()){
				setY(myGame.getHeight() - getHeight());
			}
		}
	}

	private void HorizontalSwim(double d) {
		double newX = getX() + d;
		if(newX<= myGame.getWidth()-getWidth() && newX >= 0)
		this.moveX(d);
	}
	
	private void VerticalSwim(double d) {
		double newY = getY()+d;
		if(newY <= myGame.getHeight()-getHeight() && newY >= 0)
		this.moveY(d);
	}
	

}
