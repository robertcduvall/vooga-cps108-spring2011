package games.pong.sprites;

import java.awt.image.BufferedImage;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class PlayerPaddle extends AbstractPaddle{
	private VoogaGame game;
	private Boolean BallInPlay;
	
	public PlayerPaddle(VoogaGame game, double x, double y) {
		super(game.getImageLoader().getImage("paddle"), x, y);
		health=5;
		
		this.game = game;
		BallInPlay = false;
        game.registerEventHandler("Input.Player.Up", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                //System.out.println("up!");
            	//setY(getY()-5);
            	moveUp();
            }            
        });
        
        game.registerEventHandler("Input.Player.Down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	//System.out.println("down!");
            	//if(getCenterY()+height/2 < game.getHeight())
            	//setY(getY()+5);
            	moveDown();
            }            
        });
        
        game.registerEventHandler("DecreasePlayerHealth.bounds", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	decreaseHealth();
            	System.out.println("player HEalth: "+health);
            }            
        });		
        
        game.registerEventHandler("StartNewBall", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	if(!BallInPlay){
            		createNewBall();
            		BallInPlay = true;
            	}
            	
            }            
        });
        
        game.registerEventHandler("BallExitsRight", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	BallInPlay = false;
            }            
        });
        
        game.registerEventHandler("BallExitsLeft", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
            	BallInPlay = false;
            }            
        });

	}
	
	public void moveDown() {
		if(getCenterY()+height/2 < game.getHeight())
			setY(getY()+5);
	}
	
	public void moveUp() {
		if(getCenterY()-height/2 > 0)
			setY(getY()-5);
	}
	
	public void createNewBall() {
		Ball ball = (Ball) game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX()+10, (int) getY());
		ball.setSpeed(.75, 0);
		//game.getLevelManager().addArchetypeSprite("ball", (int) getCenterX()+10, (int) getY());
		game.fireEvent(this, "AIstart", game);
	}
	
	

}
