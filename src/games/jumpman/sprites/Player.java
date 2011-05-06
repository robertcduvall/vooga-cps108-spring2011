package games.jumpman.sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.MasslessPhysicsC;

/**
 * 
 * JumpMan player. Credit to both Keen and Frogger- their Sprites
 * are a big part of this.
 * 
 * @author Dave
 *
 */
@SuppressWarnings("serial")
public class Player extends Sprite {

	private boolean canJump= true;
	private boolean canDoubleJump=true;
	private VoogaGame myGame;
	
	private static final double JUMP_VELOCITY = -0.3;

	public Player(BufferedImage image, int playerX, int playerYStart,
			VoogaGame game) {
		
		super(image, playerX, playerYStart);
		addComponent(new MasslessPhysicsC());
		
		oldX = playerX;
		oldY = playerYStart;
		
		myGame=game;
		registerKeypresses();
	}
	
	private void registerKeypresses(){

		myGame.registerEventHandler("Input.User.Jump", new IEventHandler() {
			@Override
			public void handleEvent(Object o) {
				jump();
			}
		});
	}
	

	private void jump() {
		if(canJump){
			super.setSpeed(0, JUMP_VELOCITY);
			canJump = false;
			System.out.println("Jumped!");
		}
		else{
			if(canDoubleJump){

				super.setSpeed(0, JUMP_VELOCITY*.75);
				canDoubleJump = false;
				System.out.println("Double Jumped!");
			}
		}
	}

	public void hitSurface() {
		handleCollision();
		
	}
	
	public void revertPosition() {
		setX(getOldX());
		setY(getOldY());
	}
	
	public void handleCollision() {
		canJump  = true;
		canDoubleJump  = true;
		setVerticalSpeed(0);
		revertPosition();
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		if(y<-500||y>500){
			myGame.fireEvent(this, "Game.SpawnPlayer");
			setActive(false);
		}
	}
	
	@Override
	public void render(Graphics2D graphics){
		graphics.drawImage(image, (int)x, (int)y, null);
	}

}
