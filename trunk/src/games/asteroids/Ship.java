package games.asteroids;

/**
 * @author Charlie Hatcher, Conrad Haynes, Yin Xiao, Lingzhao Xie
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vooga.core.VoogaGame;
import vooga.core.event.EventManager;
import vooga.core.event.IEventHandler;
import vooga.core.event.examples.VoogaExampleGame;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Ship extends Sprite {
	
	interface Weapon{
		void shoot();
	}

	
	private static final int MOVE_DOWN = 1;
    private static final int MOVE_LEFT = -1;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_UP = -1;

    private VoogaExampleGame vGame;
    private EventManager myManager;
    private BufferedImage ImageShot;
    private Weapon myCurrentWeapon;
    private int myDirection;
    private VoogaGame myGame;
    private SpriteGroup myShots;
    private Sprite mySprite;
    
    
    public Ship(){
    	vGame = new VoogaExampleGame() {
			
			@Override
			public void updatePlayField(long elapsedTime) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void render(Graphics2D g) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void initResources() {
				// TODO Auto-generated method stub
				
			}
		};
    	myManager = new EventManager();
    	myManager.registerEventHandler("EveryTurn.thrust.up",  new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                moveUp();
            }

        });
    	myManager.registerEventHandler("EveryTurn.thrust.down", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                moveDown();
            }

        });
    	myManager.registerEventHandler("EveryTurn.thrust.left",new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                moveLeft();
            }

        });
    	myManager.registerEventHandler("EveryTurn.thrust.right",new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                moveRight();
            }

        });
    	myManager.registerEventHandler("EveryTurn.shoot",new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                fireWeapon();
            }

        });
    	myCurrentWeapon = new Weapon(){

			@Override
			public void shoot() {
				Sprite newShot = new Sprite(ImageShot);
                newShot.setX(mySprite.getX());
                newShot.setY(mySprite.getY());
                newShot.setVerticalSpeed(1);
                myShots.add(newShot);
			}
    		
    	};
    	
    	
    }
    public void fireWeapon ()
    {
        myCurrentWeapon.shoot();
    }
    
    public void moveDown ()
    {
        mySprite.moveY(MOVE_DOWN);
    }


    public void moveLeft ()
    {
        myDirection = MOVE_LEFT;
        mySprite.moveX(MOVE_LEFT);
    }


    public void moveRight ()
    {
        myDirection = MOVE_RIGHT;
        mySprite.moveX(MOVE_RIGHT);
    }
    
    public void moveUp ()
    {
        mySprite.moveY(MOVE_UP);
    }

	public void damage() {
		// TODO Auto-generated method stub
		
	}
    
}
