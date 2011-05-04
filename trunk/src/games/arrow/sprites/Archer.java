package games.arrow.sprites;

import games.arrow.sprites.components.MouseRotateC;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Archer extends Sprite implements IPowerupable{

	
	@Override
	public Double setAngle(double dAngle) {
		return super.setAngle(dAngle-135);
	}

	private VoogaGame myGame;
	private double myTension;

	public Archer (VoogaGame game)
    {
    	super(game.getImageLoader().getImage("archer"));
        myGame = game;
        addComponent(new MouseRotateC(game));
        bindKeys();
    }
	
	private void bindKeys ()
    {
        myGame.registerEventHandler("Input.User.Pull", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                pullBowstring();
            }
        });
        myGame.registerEventHandler("Input.User.Release", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                releaseBowstring();
            }
        });
        myGame.registerEventHandler("Input.User.Fire", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                fire();
            }
        });
        myGame.registerEventHandler("Input.User.moveLeft", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                moveLeft();
            }
        });
        myGame.registerEventHandler("Input.User.moveRight", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                moveRight();
            }
        });
       
    }

	protected void fire() {
		if (myTension <= 0) return;
		System.out.println("Fire!");
		Sprite arrow = myGame.getLevelManager().addArchetypeSprite("arrow", (int)getCenterX(), (int)getCenterY());
	    arrow.move(-arrow.getWidth()/2, -arrow.getHeight()/2);
	    arrow.setMovement(myTension*.5, this.getAngle()+130);
	    releaseBowstring();
	}

	protected void releaseBowstring() {
		myTension = 0;
	}

	

	protected void pullBowstring() {
		
		myTension = myTension >= 1 ? myTension : (myTension + .01);
		
		
	}
	
	protected void moveRight() {
		this.moveX(1);
	}

	protected void moveLeft() {
		this.moveX(-1);
	}
	
	public void die(){
		//TODO end game here, make fun of idiot for comitting suicide
	}
}
