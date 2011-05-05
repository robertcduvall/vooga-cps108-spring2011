package games.arrow.sprites;

import games.arrow.sprites.components.MouseRotateC;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.builder.SpriteBuilder;
import vooga.sprites.spritebuilder.components.basic.PermAccelerationC;

public class Archer extends GoodSprite implements IPowerupable{

	
	@Override
	public Double setAngle(double dAngle) {
		return super.setAngle(dAngle+45);
	}

	private VoogaGame myGame;
	private double myTension;
	private SpriteBuilder<Arrow> ArrowBuilder;

	public Archer (VoogaGame game)
    {
    	super(game.getImageLoader().getImage("archer"));
        myGame = game;
        ArrowBuilder = new SpriteBuilder<Arrow>(PermAccelerationC.class);
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
                fire(getAngle()+45);
                releaseBowstring();
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
        myGame.registerEventHandler("Input.User.multishot", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                mutlishot();
                releaseBowstring();
            }
        });
       
    }

	protected void mutlishot() {
		for (int i = -30; i < 30; i+=15){
			fire(this.getAngle()+i+45);
		}
	}

	protected void fire(Double angle) {
		Sprite arrow = ArrowBuilder.buildSprite((Arrow)myGame.getLevelManager().addArchetypeSprite("arrow", (int)getCenterX(), (int)getCenterY()), 0.0, .0001);
	    arrow.move(-arrow.getWidth()/2, -arrow.getHeight()/2);
	    arrow.setMovement(myTension*.5, angle);
	    
	}

	protected void releaseBowstring() {
		myTension = 0;
	}

	

	protected void pullBowstring() {
		
		myTension = myTension >= 2 ? myTension : (myTension + .02);
		
	}
	
	protected void moveRight() {
		
		if (this.getX() < myGame.getWidth()-this.getWidth()) this.moveX(2);
	}

	protected void moveLeft() {
		if (this.getX() > 0) this.moveX(-2);
	}
	
	public void die(){
		//TODO end game here, make fun of idiot for comitting suicide
	}
}
