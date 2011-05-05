package games.starshipdefender.gameobjects;

import vooga.core.VoogaGame;
import vooga.core.event.IEventHandler;
import vooga.sprites.improvedsprites.Sprite;

public class TargetingImage extends Sprite
{
    private VoogaGame myGame;
    public static int myHealth;
    
    public TargetingImage(VoogaGame game)
    {
        super(game.getImageLoader().getImage("crosshairs"));
        myGame = game;
        this.setLocation(myGame.getWidth()/2, myGame.getHeight()/2);
        
        myHealth = 100;
        
        this.createEventHandler();
    }
    
    private void createEventHandler()
    {
        myGame.registerEventHandler("Input.User.Target.Up", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                moveTargetUp();
            }
        });
        
        myGame.registerEventHandler("Input.User.Target.Down", new IEventHandler()
        {
            @Override
            public void handleEvent(Object o)
            {
                moveTargetDown();
            }
            
        });
        
        myGame.registerEventHandler("Input.User.Target.Left", new IEventHandler()
        {

            @Override
            public void handleEvent(Object o)
            {
                moveTargetLeft();
                
            }
            
        });
        
        myGame.registerEventHandler("Input.User.Target.Right", new IEventHandler()
        {

            @Override
            public void handleEvent(Object o)
            {
                moveTargetRight();
                
            }
            
        });
        
        myGame.registerEventHandler("Input.User.Target.Shoot", new IEventHandler()
        {

            @Override
            public void handleEvent(Object o)
            {
                shoot();
            }
            
        });
        
    }
    
    private void shoot()
    {
        Sprite shot = myGame.getLevelManager().addSpriteFromPool("starshipdefender.gameobjects.Torpedo");
        shot.setLocation(0, myGame.getHeight());
        shot.moveTo(myGame.bsTimer.getTime(), this.getX(), this.getY());
    }
    
    private void moveTargetUp()
    {
        this.moveY(-10);
    }
    
    private void moveTargetDown()
    {
        this.moveY(10);
    }
    
    private void moveTargetLeft()
    {
        this.moveX(-10);
    }
    
    private void moveTargetRight()
    {
        this.moveX(10);
    }
}
