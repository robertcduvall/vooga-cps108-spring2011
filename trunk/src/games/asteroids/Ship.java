package games.asteroids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.golden.gamedev.util.ImageUtil;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;
import vooga.sprites.spritebuilder.components.basic.AccelerationC;
import vooga.sprites.spritebuilder.components.basic.DecelerationC;
import vooga.util.buildable.components.predefined.basic.HealthC;
import vooga.util.buildable.components.predefined.movement.Speed2DC;


public class Ship extends Sprite
{
		


	private static final long serialVersionUID = 1L;
    private VoogaGame myGame;


    public Ship (VoogaGame game)
    {
    	super(game.getImageLoader().getImage("ship"));
        myGame = game;
        // TODO setImage(game.getImageLoader().getImage("ship"));
        bindKeys();
        this.addComponents(new HealthC(42.0), new DecelerationC(.0005));
        
    }


    private void bindKeys ()
    {
        myGame.registerEventHandler("Input.User.Thrust", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                thrust();
            }
        });
        myGame.registerEventHandler("Input.User.TurnLeft", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                turnLeft();
            }
        });
        myGame.registerEventHandler("Input.User.TurnRight", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                turnRight();
            }
        });
        myGame.registerEventHandler("Input.User.Shoot", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                shoot(getAngle());
            }
        });
        myGame.registerEventHandler("Input.User.Cheat", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                powerupShoot();
            }

			
        });
    }


    private void powerupShoot() {
    	for (int i = 0; i < 360; i+= 10){
    		shoot(this.getAngle()+i);
    	}
	}
    
    public void damage ()
    {
        getComponent(HealthC.class).decrease(1.0);
        if(getComponent(HealthC.class).isDead()) {
//        	myGame.playerDied();
        }
    }


    public void shoot (double angle)
    {
       Sprite bullet = myGame.getLevelManager().addArchetypeSprite("bullet", (int)getCenterX(), (int)getCenterY());
       bullet.move(-bullet.getWidth(), -bullet.getHeight());
       bullet.setMovement(.05, angle);
    }


    public void thrust ()
    {
        super.accelerate(.001);
        //TODO PHYSICS!
    }


    public void turnLeft ()
    {
       this.setAngle(getAngle() - 2);
    }


    public void turnRight ()
    {
        setAngle(getAngle() + 2);
    }
}
