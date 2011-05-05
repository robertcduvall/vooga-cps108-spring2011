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
	@Override
	public void render(Graphics2D g,int x,int y) {
		AffineTransform aTransform = new AffineTransform();
        aTransform.translate((int) this.getX() +width/2, 
                             (int) this.getY()+height/2);
        aTransform.rotate(Math.toRadians(this.getAngle()+90));
        
        aTransform.translate((int) -width/2, 
                             (int) -height/2);
        if (this.getHorizontalSpeed() < 0) g.drawImage(ImageUtil.flip(ImageUtil.resize(image, width, height)),aTransform,null);
        else g.drawImage(ImageUtil.resize(image, width, height),aTransform,null);
        super.renderComponents(g, x, y);
        g.setColor(Color.BLACK);
		this.getCollisionShape().render(g);
	}
		


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
        super.setAbsoluteSpeed(.1);
        //TODO PHYSICS!
    }


    public void turnLeft ()
    {
       this.setAngle(getAngle() - 1);
    }


    public void turnRight ()
    {
        setAngle(getAngle() + 1);
    }
}
