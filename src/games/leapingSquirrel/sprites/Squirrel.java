package games.leapingSquirrel.sprites;

import games.leapingSquirrel.LeapingSquirrelGame;
import java.awt.image.BufferedImage;
import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class Squirrel extends Sprite
{
    private VoogaGame game;
    private static final long serialVersionUID = 1L;
    public static boolean HAS_STARTED = false;
    private static boolean FACING_RIGHT = true;
    private static boolean VIKING_HAT = false;
    private static double HORIZONTAL_SPEED = 0.32;
    
    public Squirrel(BufferedImage image, int x, int y, VoogaGame game){
        super(image, x, y);
        
        this.game = game;
        game.registerEventHandler("Input.User.Left", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            { 
               if(FACING_RIGHT)
               {
                   //Change squirrel image
                   // this.setImage(getResourceManager().getImageLoader().getImage("Squirrel_LEFT"));
                   FACING_RIGHT = false;
               }
               moveLeft();
            }            
        });
        
        game.registerEventHandler("Input.User.Right", new IEventHandler()
        {
            @Override
            public void handleEvent (Object o)
            {
                if(!FACING_RIGHT)
                {
                    //Change squirrel image
                    // this.setImage(game.getResourceManager().getImageLoader().getImage("Squirrel_LEFT"));
                    FACING_RIGHT = true;
                }
                moveRight();
            }            
        });
        
        game.registerEventHandler("Input.User.Space", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                System.out.println("Jumped");
                //Fix this to accommodate the fact that he needs to be on the ground.
                jump();
            }
        });
    }
    
    private void moveLeft() {
        this.setHorizontalSpeed(HORIZONTAL_SPEED*-1);
    }
    
    private void moveRight() {
        this.setHorizontalSpeed(HORIZONTAL_SPEED);
    }
    
    private void jump() {
        this.setVerticalSpeed(-0.6);
        this.accelerate(0.1);
    }
}
