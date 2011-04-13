package games.asteroids.resources;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vooga.core.VoogaGame;


import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Ship {
	
	interface Weapon{
		void shoot();
	}

	private static final int MOVE_DOWN = 1;
    private static final int MOVE_LEFT = -1;
    private static final int MOVE_RIGHT = 1;
    private static final int MOVE_UP = -1;

    private BufferedImage ImageShot;
    private Weapon myCurrentWeapon;
    private int myDirection;
    private VoogaGame myGame;
    private SpriteGroup myShots;
    private Sprite mySprite;
    
    
    public Ship(){
    	
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
    
}
