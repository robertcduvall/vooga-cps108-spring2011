package games.frogger.sprites;

import games.frogger.Frogger;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Frog extends Sprite {

	private VoogaGame game;
	private boolean activeFrog;
	private static final int FROG_DELTA = 42;
	
	private static final long serialVersionUID = 1L;

	public Frog(BufferedImage image, int x, int y, VoogaGame game){
		super(image, x, y);
		
		this.game = game;
		activeFrog = true;
		
		
		game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if(activeFrog) {
            		moveLeft();
            	}
            }            
        });
		
		game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if(activeFrog) {
            		moveRight();
            	}
            }            
        });
		
		game.registerEventHandler("Input.User.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if(activeFrog) {
            		moveUp();
            	}
            }            
        });
		
		game.registerEventHandler("Input.User.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if(activeFrog) {
            		moveDown();
            	}
            }            
        });
	}
	
	public boolean isActiveFrog() {
		return activeFrog;
	}
	
	public void setActiveFrog(boolean state) {
		activeFrog = state;
	}
	
	private void moveLeft() {
		this.setAngle(180.0);
		if(this.getX() - this.getWidth()/2 >= 0) {
			this.moveX(-FROG_DELTA);
		}
	}
	
	private void moveRight() {
		this.setAngle(0.0);
		if(this.getX() + this.getWidth() <= game.getWidth()) {
			this.moveX(FROG_DELTA);
		}
	}
	
	private void moveUp() {
		this.setAngle(270.0);
		if(this.getY() - 2*this.getHeight() >= 0) {
			this.moveY(-FROG_DELTA);
		}
	}
	
	private void moveDown() {
		this.setAngle(90.0);
		if(this.getY() + this.getHeight() <= ((Frogger) game).FROG_Y_START) {
			this.moveY(FROG_DELTA);
		}
	}
	
}
