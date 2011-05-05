package games.jumper.sprites;

import java.awt.Graphics2D;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.resources.Direction;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.physics.MasslessPhysicsC;
import vooga.sprites.spritebuilder.components.physics.PhysicsVelocityC;

@SuppressWarnings("serial")
public class Avatar extends Sprite{
	
	private static final double AVATAR_JUMP = -3;
	private static final double X_LOCATION = 100;
	private static final double Y_LOCATION = 300;
	private VoogaGame myGame;
	private int myLives;
	public static final Double Avatar_Speed = 2D;
	MasslessPhysicsC myPhysics;

	public Avatar(VoogaGame game){
		super(game.getImageLoader().getImage("mario"));
		setX(X_LOCATION);
		setY(Y_LOCATION);
		myPhysics = new MasslessPhysicsC();
		this.addComponent(myPhysics);
		this.myGame = game;
		myLives = 3;
		setAngle(Direction.NORTH.getAngle());
		
		game.registerEventHandler("Input.User.Left", new IEventHandler() {
			
			@Override
			public void handleEvent(Object o) {
				shift(-Avatar_Speed);
			}
		});
		
		game.registerEventHandler("Input.User.Right", new IEventHandler() {
			
			@Override
			public void handleEvent(Object o) {
				shift(Avatar_Speed);
			}
		});
		
		game.registerEventHandler("Input.User.Jump", new IEventHandler() {
			
			@Override
			public void handleEvent(Object o) {
				moveY(AVATAR_JUMP);
			}
		});
		
		game.registerEventHandler("Game.Avatar.Died", new IEventHandler() {
			
			@Override
			public void handleEvent(Object o) {
				startLevelOver();
			}
		});
	}
	
    /**
     * Move the paddle, if the screen boundaries allow for it.
     * @param dx The difference in x-coordinate.
     */
    protected void shift (double dx)
    {
        double newX = getX() + dx;
        
        if (0 < newX && newX + getWidth() < myGame.getWidth())
            moveX(dx);
    }

	private void startLevelOver() {
		if(myLives==0){
			System.out.println("GameOver");
			System.exit(0);
		}
		myLives--;
		this.setX(X_LOCATION);
		this.setY(Y_LOCATION);
	}
	
	public void stopMoving(){
		this.setVerticalSpeed(0);
		this.setAngle(Direction.SOUTH.getAngle());
		System.out.println("mySpeed = " + this.getVerticalSpeed());
	}
	

}
